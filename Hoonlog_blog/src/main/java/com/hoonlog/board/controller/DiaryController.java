package com.hoonlog.board.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hoonlog.board.service.DiaryReplyService;
import com.hoonlog.board.service.DiaryService;
import com.hoonlog.board.utils.PageMaker;
import com.hoonlog.board.utils.SearchCriteria;
import com.hoonlog.board.vo.DiaryReplyVO;
import com.hoonlog.board.vo.DiaryVO;
import com.hoonlog.member.vo.MemberVO;


@Controller
@RequestMapping(value="/diary")
public class DiaryController {
	
	private static final Logger logger = LoggerFactory.getLogger(DiaryController.class);
	
	@Inject
	private DiaryService diaryService;
	
	@Inject
	DiaryReplyService replyService;

	
	/* 게시글 목록 */
	@RequestMapping(value="/diaryList", method= RequestMethod.GET)
	public String diaryList(Model model, @ModelAttribute("scri") SearchCriteria scri) throws Exception{
		
		logger.info("게시판 목록 페이지 진입");
		
		model.addAttribute("diaryList", diaryService.diaryList(scri));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(diaryService.diaryListCnt(scri));
		
		model.addAttribute("pageMaker", pageMaker);
		
		return "diary/diaryList";
	}

	/* 게시글 작성(이동) */
	@RequestMapping(value="/diaryWrite", method=RequestMethod.GET)
	public String diaryWrite() {
		
		logger.info("게시판 작성 페이지 진입");
		
		return "diary/diaryWrite";
	}
	
	/* 게시글 작성(저장) */
	@RequestMapping(value = "/saveDiary", method = RequestMethod.POST)
	public String saveDiary(DiaryVO diary, RedirectAttributes rttr, Model model, MultipartHttpServletRequest mpRequest) throws Exception {
		
		logger.info("게시판 작성 저장");
		
		diaryService.insertDiary(diary, mpRequest);

		return "redirect:/diary/diaryList";
	}

	/* 게시글 내용 보기 */
	@RequestMapping(value = "/diaryView", method = RequestMethod.GET)
	public String diaryView(Model model, DiaryVO diary,  @ModelAttribute("scri") SearchCriteria scri, @RequestParam("bno") int bno) throws Exception {
		
		logger.info("게시글 보기");
		
		model.addAttribute("diaryView", diaryService.diaryView(diary.getBno()));
		model.addAttribute("scri", scri);

		List<Map<String, Object>> fileList = diaryService.selectFileList(diary.getBno());
		model.addAttribute("file", fileList);
		

		return "diary/diaryView";
	}
	
	/* 게시글 수정(이동) */
	@RequestMapping(value = "/diaryUpdate", method = RequestMethod.GET)
	public String diaryUpdate(DiaryVO diary, Model model,  @ModelAttribute("scri") SearchCriteria scri) throws Exception {
		
		logger.info("게시판 수정 페이지 이동");
		
		model.addAttribute("update", diaryService.diaryView(diary.getBno()));
		model.addAttribute("scri", scri);
		
		List<Map<String, Object>> fileList = diaryService.selectFileList(diary.getBno());
		model.addAttribute("file", fileList);
		return "diary/diaryUpdate";	
	}
	
	/* 게시글 수정(저장) */
	@RequestMapping(value = "/diaryUpdate", method = RequestMethod.POST)
	public String saveUpdate(DiaryVO diary,  @ModelAttribute("scri") SearchCriteria scri, RedirectAttributes rttr, 
			@RequestParam(value="fileNoDel[]") String[] files,
			 @RequestParam(value="fileNameDel[]") String[] fileNames, MultipartHttpServletRequest mpRequest) throws Exception {
		
		logger.info("게시판 수정 저장");
		
		diaryService.updateDiary(diary, files, fileNames, mpRequest);
		
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		
		return "redirect:/diary/diaryView?bno="+diary.getBno();
	}

	/* 게시글 삭제 */
	@RequestMapping(value = "/diaryDelete", method = RequestMethod.GET)
	public String diaryDelete(RedirectAttributes rttr,  @ModelAttribute("scri") SearchCriteria scri, DiaryVO diary) throws Exception {
		
		logger.info("게시판 삭제");
		
		diaryService.deleteDiary(diary.getBno());
		
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		
		return "redirect:/diary/diaryList";
	}
	
	/* 파일 업로드 */
	@RequestMapping(value="/fileDown")
	public void fileDown(@RequestParam Map<String, Object> map, HttpServletResponse response) throws Exception{
		
		logger.info("파일 업로드 진행");
		
		Map<String, Object> resultMap = diaryService.selectFileInfo(map);
		String storedFileName = (String) resultMap.get("STORED_FILE_NAME");
		String originalFileName = (String) resultMap.get("ORG_FILE_NAME");
		
		// 파일을 저장했던 위치에서 첨부파일을 읽어 byte[]형식으로 변환한다.
		byte fileByte[] = FileUtils.readFileToByteArray(new File("C:\\upload\\"+storedFileName));
		
		response.setContentType("application/octet-stream");
		response.setContentLength(fileByte.length);
		response.setHeader("Content-Disposition",  "attachment; fileName=\""+URLEncoder.encode(originalFileName, "UTF-8")+"\";");
		response.getOutputStream().write(fileByte);
		response.getOutputStream().flush();
		response.getOutputStream().close();
		
	}
	
	/* CK에디터 이미지 업로드 */
	@RequestMapping(value="/imageUpload", method = RequestMethod.POST)
    public void imageUpload(HttpServletRequest request,
            HttpServletResponse response, MultipartHttpServletRequest multiFile
            , @RequestParam MultipartFile upload) throws Exception{
        
		logger.info("ck에디터 이미지 업로드");
		
		// 랜덤 문자 생성
        UUID uid = UUID.randomUUID();
        
        OutputStream out = null;
        PrintWriter printWriter = null;
        
        //인코딩
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        
        try{
            
            //파일 이름 가져오기
            String fileName = upload.getOriginalFilename();
            byte[] bytes = upload.getBytes();
            
            //이미지 경로 생성
            String path = "C://upload//" + "ckImage//";// fileDir는 전역 변수라 그냥 이미지 경로 설정해주면 된다.
            String ckUploadPath = path + uid + "_" + fileName;
            File folder = new File(path);
            
            //해당 디렉토리 확인
            if(!folder.exists()){
                try{
                    folder.mkdirs(); // 폴더 생성
                }catch(Exception e){
                    e.getStackTrace();
                }
            }
            
            out = new FileOutputStream(new File(ckUploadPath));
            out.write(bytes);
            out.flush(); // outputStram에 저장된 데이터를 전송하고 초기화
            
            String callback = request.getParameter("CKEditorFuncNum");
            printWriter = response.getWriter();
            String fileUrl = "/diary/ckImgSubmit?uid=" + uid + "&fileName=" + fileName;  // 작성화면
            
        // 업로드시 메시지 출력
          printWriter.println("{\"filename\" : \""+fileName+"\", \"uploaded\" : 1, \"url\":\""+fileUrl+"\"}");
          printWriter.flush();
            
        }catch(IOException e){
            e.printStackTrace();
        } finally {
          try {
           if(out != null) { out.close(); }
           if(printWriter != null) { printWriter.close(); }
          } catch(IOException e) { e.printStackTrace(); }
         }
        
        return;
    }
    
	/* ck에디터 이미지 저장 */
	 @RequestMapping(value="/ckImgSubmit")
	    public void ckSubmit(@RequestParam(value="uid") String uid
	                            , @RequestParam(value="fileName") String fileName
	                            , HttpServletRequest request, HttpServletResponse response)
	 throws ServletException, IOException{
		 
		 logger.info("ck에디터 이미지 저장");
		 
		 //서버에 저장된 이미지 경로
	        String path = "C://upload//" + "ckImage/";
	    
	        String sDirPath = path + uid + "_" + fileName;
	    
	        File imgFile = new File(sDirPath);
	        
	        //사진 이미지 찾지 못하는 경우 예외처리로 빈 이미지 파일을 설정한다.
	        if(imgFile.isFile()){
	            byte[] buf = new byte[1024];
	            int readByte = 0;
	            int length = 0;
	            byte[] imgBuf = null;
	            
	            FileInputStream fileInputStream = null;
	            ByteArrayOutputStream outputStream = null;
	            ServletOutputStream out = null;
	            
	            try{
	                fileInputStream = new FileInputStream(imgFile);
	                outputStream = new ByteArrayOutputStream();
	                out = response.getOutputStream();
	                
	                while((readByte = fileInputStream.read(buf)) != -1){
	                    outputStream.write(buf, 0, readByte);
	                }
	                
	                imgBuf = outputStream.toByteArray();
	                length = imgBuf.length;
	                out.write(imgBuf, 0, length);
	                out.flush();
	       
	            	}catch(IOException e){
	            }
	        }
	 }
	 
	
	// (댓글) 작성
	 @ResponseBody
	 @RequestMapping(value = "/replyWrite", method = RequestMethod.POST)
	 public void replyWrite(DiaryReplyVO reply, HttpSession session) throws Exception {
	  logger.info("regist reply");
	  
	  MemberVO member = (MemberVO)session.getAttribute("member");
	  reply.setUserid(member.getUserid());
	  
	  replyService.replyWrite(reply);
	  
	 }
	 
	// 상품 소감(댓글) 목록
	 @ResponseBody
	 @RequestMapping(value = "/replyList", method = RequestMethod.GET)
	 public List<DiaryReplyVO> getReplyList(@RequestParam("bno") int bno) throws Exception {
	  logger.info("get reply list");
	    
	  List<DiaryReplyVO> reply = replyService.replyList(bno);
	  
	  return reply;
	 } 
	 
	// 상품 소감(댓글) 삭제
	 @ResponseBody
	 @RequestMapping(value = "/deleteReply", method = RequestMethod.POST)
	 public int getDeleteReply(DiaryReplyVO reply,  HttpSession session) throws Exception {
	  logger.info("post delete reply");

	  int result = 0;
	  
	  MemberVO member = (MemberVO)session.getAttribute("member");
	  String userid = replyService.idCheck(reply.getRno());
	    
	  if(member.getUserid().equals(userid)) {
	   
	   reply.setUserid(member.getUserid());
	   replyService.deleteReply(reply);
	   
	   result = 1;
	  }
	  
	  return result; 
	 }
	 
	 @ResponseBody
	 @RequestMapping(value = "/updateReply", method = RequestMethod.POST)
	 public int modifyReply(DiaryReplyVO reply, HttpSession session) throws Exception {
	  logger.info("modify reply");
	  
	  int result = 0;
	  
	  MemberVO member = (MemberVO)session.getAttribute("member");
	  String userid = replyService.idCheck(reply.getRno());
	  
	  if(member.getUserid().equals(userid)) {
	   
	   reply.setUserid(member.getUserid());
	   replyService.updateReply(reply);
	   result = 1;
	  }
	  
	  return result;
	 } 
	 
	 
	 
	 
}


	

