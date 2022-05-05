package online.jvm.servlet;

import online.jvm.bean.ProgramInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ExecuteJavaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object program = req.getParameter("program");
        Object jvmParas = req.getParameter("jvmargs");

        if (program == null) {
            return;
        }

        String projectRoot = req.getServletContext().getRealPath(".");
        ProgramInfo programInfo = writeProgram(projectRoot, program.toString());
        String executeResult = executeProgram(programInfo, jvmParas);
        resp.setContentType("text/html;charset=gbk");
        resp.getWriter().write(executeResult);
    }

    private String executeProgram(ProgramInfo programInfo, Object jvmparas) {
        try {
            ProcessBuilder pb = new ProcessBuilder();
            pb.command("javac", "-encoding", "UTF-8", programInfo.getFileName() + ".java");
            pb.directory(new File(programInfo.getFilePath()));
            Process compileProcess = pb.start();
            int compileStatus = compileProcess.waitFor();
            //编译出错
            if (compileStatus != 0) {
                String insResult = getResult(compileProcess.getInputStream());
                String errorResult = getResult(compileProcess.getErrorStream());
                return insResult + errorResult;
            }
            List<String> commands = new ArrayList<>();
            commands.add("java");
            if (jvmparas != null) {
                String[] paras = jvmparas.toString().split("\n");
                for (String para : paras) {
                    if (para != null) {
                        commands.add(para.trim());
                    }
                }
            }
            commands.add("TestFile");
            ProcessBuilder execPb = new ProcessBuilder();
            execPb.command(commands);
            execPb.directory(new File(programInfo.getFilePath()));
            Process execProcess = execPb.start();
            compileProcess.waitFor();
            return getResult(execProcess.getInputStream()) + getResult(execProcess.getErrorStream());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return "";
    }

    private String getResult(InputStream stream) throws IOException {
        InputStreamReader isr = new InputStreamReader(stream, Charset.forName("GBK"));
        BufferedReader br = new BufferedReader(isr);
        String line;

        StringBuilder sBuilder = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sBuilder.append(line).append("\n");
        }

        return sBuilder.toString();
    }

    private ProgramInfo writeProgram(String projectRoot, String program) {
        PrintWriter printWriter = null;
        try {
            String fileName = "TestFile" + System.currentTimeMillis();
            printWriter = new PrintWriter(projectRoot + File.separator + fileName + ".java");
            printWriter.print(program);
            printWriter.flush();

            return new ProgramInfo(projectRoot, fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
        return null;
    }
}
