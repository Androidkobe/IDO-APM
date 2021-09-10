package com.asm.plugin;import com.android.build.api.transform.DirectoryInput;import com.android.build.api.transform.Format;import com.android.build.api.transform.JarInput;import com.android.build.api.transform.QualifiedContent;import com.android.build.api.transform.Transform;import com.android.build.api.transform.TransformException;import com.android.build.api.transform.TransformInput;import com.android.build.api.transform.TransformInvocation;import com.android.build.api.transform.TransformOutputProvider;import com.android.build.gradle.internal.pipeline.TransformManager;import com.android.utils.FileUtils;import java.io.File;import java.io.FileOutputStream;import java.io.IOException;import java.io.InputStream;import java.util.Collection;import java.util.Enumeration;import java.util.Map;import java.util.Set;import java.util.jar.JarEntry;import java.util.jar.JarFile;import java.util.jar.JarOutputStream;import java.util.regex.Matcher;public class AsmTransform extends Transform {    private final String TAG = "ASM-TransFor";    @Override    public String getName() {        return TAG;    }    @Override    public Set<QualifiedContent.ContentType> getInputTypes() {        return TransformManager.CONTENT_CLASS;    }    @Override    public Set<? super QualifiedContent.Scope> getScopes() {        return TransformManager.SCOPE_FULL_PROJECT;    }    @Override    public boolean isIncremental() {        return false;    }    @Override    public void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {        super.transform(transformInvocation);        //当前是否是增量编译        boolean isIncremental = transformInvocation.isIncremental();        System.out.println("current is incremental = "+isIncremental);        //消费型输入，可以从中获取jar包和class文件夹路径。需要输出给下一个任务        Collection<TransformInput> inputs = transformInvocation.getInputs();        //引用型输入，无需输出。        Collection<TransformInput> referencedInputs = transformInvocation.getReferencedInputs();        //OutputProvider管理输出路径，如果消费型输入为空，你会发现OutputProvider == null        TransformOutputProvider outputProvider = transformInvocation.getOutputProvider();        for(TransformInput input : inputs) {            for(JarInput jarInput : input.getJarInputs()) {                File dest = outputProvider.getContentLocation(                        jarInput.getFile().getAbsolutePath(),                        jarInput.getContentTypes(),                        jarInput.getScopes(),                        Format.JAR);                //将修改过的字节码copy到dest，就可以实现编译期间干预字节码的目的了                File inputFile = jarInput.getFile();                System.out.println("jar transform action int put class = "+inputFile.getPath() +" name "+inputFile.getName());                FileUtils.copyFile(inputFile, dest);                System.out.println("jar transform action out put class = "+dest.getAbsolutePath() +" name "+dest.getName());            }            for(DirectoryInput directoryInput : input.getDirectoryInputs()) {                File dest = outputProvider.getContentLocation(directoryInput.getName(),                        directoryInput.getContentTypes(), directoryInput.getScopes(),                        Format.DIRECTORY);               String inputFilePath = directoryInput.getFile().getAbsolutePath();                System.out.println("Directory transform path = "+inputFilePath);               File inputFile = new File(inputFilePath);                if(inputFile.isDirectory()){                    for(File inputClassFile : inputFile.listFiles()){                        System.out.println("input class = "+inputClassFile.getAbsolutePath() +" name "+inputClassFile.getName());                    }                }                //将修改过的字节码copy到dest，就可以实现编译期间干预字节码的目的了                FileUtils.copyDirectory(inputFile, dest);                System.out.println("Directory transform action class = "+dest.getAbsolutePath() +" name "+dest.getName());            }        }    }//    void processJar(File inputFile,File outputFile) {//        try {//            File bakJar = new File(file.getParent(), file.getName() + ".bak");//            JarOutputStream jarOutputStream = new JarOutputStream(new FileOutputStream(bakJar));////            JarFile jarFile = new JarFile(file);//            Enumeration<JarEntry> entries = jarFile.entries();//            while (entries.hasMoreElements()) {//                JarEntry jarEntry = entries.nextElement();////                jarOutputStream.putNextEntry(new JarEntry(jarEntry.getName()));//                InputStream is = jarFile.getInputStream(jarEntry);////                String className = jarEntry.getName();//                if (className.endsWith(".class") && !className.startsWith(applicationName)//                        && !Utils.isAndroidClass(className) && !className.startsWith("com/enjoy" +//                        "/patch")) {////                    byte[] byteCode = ClassUtils.referHackWhenInit(is);//                    String hex = Utils.hex(byteCode);//                    hexs.put(className, hex);//                    //对比缓存的md5，不一致则放入补丁//                    patchGenerator.checkClass(className, hex, byteCode);//                    jarOutputStream.write(byteCode);//                } else {//                    //输出到临时文件//                    jarOutputStream.write(IOUtils.toByteArray(is));//                }//                is.close();//                jarOutputStream.closeEntry();//            }//            jarOutputStream.close();//            jarFile.close();//            file.delete();//            bakJar.renameTo(file);//        } catch (IOException e) {//            e.printStackTrace();//        }//    }}