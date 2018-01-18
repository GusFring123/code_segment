// 文件上传demo
@Service(value = "uploadService")
public class UploadService {

    /**
     * 上传文件
     * @param request
     * @return
     */
    public Map<String, Object> uploadFile(HttpServletRequest request) {
        //结果Map
        Map<String, Object> result = new HashMap<>();
        try {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            //得到文件map对象
            Map<String, MultipartFile> files = multipartHttpServletRequest.getFileMap();
            //获取实际路径(webapp的实际硬盘路径，放在服务器下面，便于图片资源的访问)
            String realPath = request.getServletContext().getRealPath("/");
            //目录，需要上传在服务器的路径，AppConfig.getUPLOADDIR()是配置类，用resourseBundle读取配置文件便于管理。
            String realDir = realPath + "\\" + AppConfig.getUPLOADDIR();
            //文件（夹）对象
            File dir = new File(realDir);
            //如果文件夹不存在则创建文件夹
            if (!dir.exists()) {
                dir.mkdirs();
            }
            for (MultipartFile file : files.values()) {
            	//使用原始名称作为文件名
                String fileName = file.getOriginalFilename();
             	//文件路径
                imgDir = realDir + "/" + fileName;
                File targetFile = new File(imgDir);
                //不存在则创建
                if (!targetFile.exists()) {
                    targetFile.createNewFile();
                }
                //复制文件过去
                file.transferTo(targetFile);
            }

        } catch (IOException e) {
            e.printStackTrace();
            result.put("success", "0");
            return result;
        }

        result.put("success", "1");
        return result;
    }
}