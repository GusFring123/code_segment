
@Controller
@RequestMapping(value = "/download")
public class DownloadController {

    @RequestMapping(value = "/downloadAttachments")
    public ResponseEntity<byte[]> downloadAttachments(HttpServletRequest request) {
        //excel模板文件路径，前面是获取项目路径，后面是文件相对路径。
        String attachmentsPath = request.getServletContext().getRealPath("/")+ "/upload/file/message.xlsx";
        File file = new File(attachmentsPath);
        HttpHeaders httpHeaders = new HttpHeaders();
        //解决中文乱码,针对的是tomcat服务器
        httpHeaders.setContentDispositionFormData("attachment", new String("message.xlsx".getBytes("utf-8"),"iso8859-1"));

        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        try {
            return new ResponseEntity<>(FileUtils.readFileToByteArray(file), httpHeaders, HttpStatus.CREATED);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
