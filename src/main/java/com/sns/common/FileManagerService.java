package com.sns.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManagerService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//실제 업로드가 된 이미지가 저장 될 경로(서버 주소)
	//학원 경로
	public static final String FILE_UPLOAD_PATH = "D:\\하서린\\5_spring_project\\SNS\\workspace\\images/";
	
	//집 경로
	//public static final String FILE_UPLOAD_PATH = "C:\\Users\\ASUS\\Desktop\\웹개발\\5_spring_project\\SNS\\workspace\\images/";
	
	
	//input: userLoginId, file(이미지)
	//output: web imagePath
	public String saveFile(String loginId, MultipartFile file) {
		//폴더 생성
		//폴더 생성 경로의 예: aaaa_847382292
		String directoryName = loginId + "_" + System.currentTimeMillis();
		String filePath = FILE_UPLOAD_PATH + directoryName;
		
		File directory = new File(filePath);
		if (directory.mkdir() == false) {
			//폴더 생성 실패시 이미지 경로 null로 리턴
			return null;
		}
		
		
		//폴더 안에 파일 업로드 : byte단위로 업로드 한다
		try {
			byte[] bytes = file.getBytes();
			// ★★★★★★ 한글 이름 이미지는 올릴 수 없으므로 나중에 영문자로 바꿔서 올리기
			Path path = Paths.get(filePath + "/" + file.getOriginalFilename());
			Files.write(path, bytes);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		
		//파일 업로드 성공을 하면 웹 이미지 url path를 return
		//주소는 이렇게 될 것이다 라고 예언을 하는 것임 실제로 주소가 생기는 것은 아님
		//예시 형식: /images/aaa_235689431/sun.png
		return "/images/" + directoryName + "/" + file.getOriginalFilename();
		
	}
	
	// 파일 삭제 메소드
		// input: imagePath
		// output: void
		public void deleteFile(String imagePath) { // imagePath: /images/aaaa_1689841033122/beach-2179624_960_720.jpg
			// D:\\shinboram\\6_spring_project\\memo\\workspace\\images/aaaa_1689841033122/beach-2179624_960_720.jpg
			// 주소에 겹치는 /images/ 를 제거한다.

			Path path = Paths.get(FILE_UPLOAD_PATH + imagePath.replace("/images/", ""));
			if (Files.exists(path)) { // 이미지가 존재하는가?
				// 이미지 삭제
				try {
					Files.delete(path);
				} catch (IOException e) {
					logger.info("###[FileManagerService 이미지 삭제 실패] imagePath:{}", imagePath);
				}

				// 디렉토리(폴더) 삭제
				path = path.getParent();
				if (Files.exists(path)) {
					try {
						Files.delete(path);
					} catch (IOException e) {
						logger.info("###[FileManagerService 이미지 폴더 삭제 실패] imagePath:{}", imagePath);
					}
				}
			}
		}
	
}
