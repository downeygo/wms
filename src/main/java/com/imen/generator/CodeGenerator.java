package com.imen.generator;

import com.imen.wms.domain.*;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.text.MessageFormat;

public class CodeGenerator {
    private static Configuration configuration;
    private static Template template;
    static {
        try {
            //创建configuration对象
            configuration = new Configuration(Configuration.VERSION_2_3_22);
            //设置文件加载目录
            configuration.setDirectoryForTemplateLoading(new File("templates"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成文件
     * @param classInfo domain对象字节码
     * @param sourceFile 模板文件的路径
     * @param targetFile 生成的文件路径
     * @throws Exception
     */
    public static void createFile(ClassInfo classInfo,String sourceFile,String targetFile) throws Exception{
        template = configuration.getTemplate(sourceFile);
        String formatPath=MessageFormat.format(targetFile,classInfo.getBasePkg()
                .replace(".","/"),classInfo.getClassName(),classInfo.getObjectName());

        File file=new File(formatPath);
        //判断父目录是否存在
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        template.process(classInfo,new FileWriter(formatPath));
    }

    /**
     * 追加配置文件
     * @param classInfo domain对象字节码
     * @param sourceFile 模板文件的路径
     * @param targetFile 生成的文件路径
     * @throws Exception
     */
    public static void appendXML(ClassInfo classInfo,String sourceFile,String targetFile) throws Exception {
        template=configuration.getTemplate(sourceFile);
        String formatPath=MessageFormat.format(targetFile,classInfo.getBasePkg()
                .replace(".","/"),classInfo.getClassName(),classInfo.getObjectName());

        File file=new File(formatPath);
        //判断父目录是否存在
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        StringWriter out =new StringWriter();
        template.process(classInfo,out);
        XmlUtil.mergeXML(new File(targetFile),out.toString());
    }

    public static void doWork() throws Exception {
        ClassInfo classInfo=new ClassInfo(SaleAccount.class);

        //=================生成DAO,DAOImpl========================
        createFile(classInfo,"IDAO.java","src/main/java/{0}/dao/I{1}DAO.java");
        createFile(classInfo,"DAOImpl.java","src/main/java/{0}/dao/impl/{1}DAOImpl.java");

        //=================生成QueryObject====================
        createFile(classInfo,"QueryObject.java","src/main/java/{0}/query/{1}QueryObject.java");

        //=================生成Service,ServiceImpl====================
        createFile(classInfo,"IService.java","src/main/java/{0}/service/I{1}Service.java");
        createFile(classInfo,"ServiceImpl.java","src/main/java/{0}/service/Impl/{1}ServiceImpl.java");

        //=================生成action====================
        createFile(classInfo,"Action.java","src/main/java/{0}/web/action/{1}Action.java");

        //=================生成list.jsp,input.jsp====================
        createFile(classInfo,"list.jsp","src/main/webapp/WEB-INF/view/{2}/list.jsp");
        createFile(classInfo,"input.jsp","src/main/webapp/WEB-INF/view/{2}/input.jsp");

        //=================生成hbm.xml====================
        createFile(classInfo,"hbm.xml","src/main/resources/{0}/domain/{1}.hbm.xml");

        //=================生成dao.xml,service.xml,action.xml====================
        appendXML(classInfo,"dao.xml","src/main/resources/applicationContext-dao.xml");
        appendXML(classInfo,"service.xml","src/main/resources/applicationContext-service.xml");
        appendXML(classInfo,"action.xml","src/main/resources/applicationContext-action.xml");

    }

    public static void main(String[] args)throws Exception{
        //doWork();
        Ww();
        System.out.println("生成完毕");
    }

    public static void Ww()throws Exception {
        ClassInfo classInfo=new ClassInfo(SaleAccount.class);
        createFile(classInfo,"IDAO.java","src/main/java/{0}/dao/I{1}DAO.java");
        createFile(classInfo,"DAOImpl.java","src/main/java/{0}/dao/impl/{1}DAOImpl.java");
        createFile(classInfo,"hbm.xml","src/main/resources/{0}/domain/{1}.hbm.xml");

    }
}
