package org.example.springbootblog.controller;

import org.example.springbootblog.pojo.Result;
import org.example.springbootblog.utils.AliOssUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
public class FileUploadController {
	@Autowired
	private AliOssUtil aliOssUtil;
	/**
	 * 文件上传
	 * @param file
	 * @return
	 */
	@PostMapping("/upload")
	public Result<String> uploadFile(@RequestParam("file") MultipartFile file) {
		try {
			// 获取原始文件名
			String originalFilename = file.getOriginalFilename();
			// 确保文件名唯一，防止文件被覆盖
			String fileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
			//上传到阿里云
			String url = aliOssUtil.uploadFile(fileName, file.getInputStream());
			return Result.success(url);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("上传失败");
		}
	}
}
