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

import com.hoonlog.board.service.BoardService;
import com.hoonlog.board.service.BoardReplyService;
import com.hoonlog.board.utils.PageMaker;
import com.hoonlog.board.utils.SearchCriteria;
import com.hoonlog.board.vo.BoardVO;
import com.hoonlog.board.vo.BoardReplyVO;
import com.hoonlog.member.vo.MemberVO;

@Controller
@RequestMapping(value="/board")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	private BoardService boardService;
	
	@Inject
	BoardReplyService replyService;

	
	/* 게시글 목록 */
	@RequestMapping(value="/boardList", method= RequestMethod.GET)
	public String boardList(Model model, @ModelAttribute("scri") SearchCriteria scri) throws Exception{
		logger.info("게시판 목록 페이지 진입");
		
		model.addAttribute("boardList", boardService.boardList(scri));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(boardService.boardListCnt(scri));
		
		model.addAttribute("pageMaker", pageMaker);
		
		return "board/boardList";
	}

	/* 게시글 작성(이동) */
	@RequestMapping(value="/boardWrite", method=RequestMethod.GET)
	public String boardWrite() {
		logger.info("게시판 작성 페이지 진입");
		
		return "board/boardWrite";
	}
	
	/* 게시글 작성(저장) */
	@RequestMapping(value = "/saveBoard", method = RequestMethod.POST)
	public String saveBoard(BoardVO board, RedirectAttributes rttr, Model model, 
							MultipartHttpServletRequest mpRequest) throws Exception {
		logger.info("게시판 작성 저장");
		
		boardService.insertBoard(board, mpRequest);
		
		return "redirect:/board/boardList";
	}

	/* 게시글 내용 보기 */
	@RequestMapping(value = "/boardView", method = RequestMethod.GET)
	public String boardView(Model model, BoardVO board,  @ModelAttribute("scri") SearchCriteria scri, 
							@RequestParam("bno") int bno) throws Exception {
		logger.info("게시글 보기");
		
		model.addAttribute("boardView", boardService.boardView(board.getBno()));
		model.addAttribute("scri", scri);

		List<Map<String, Object>> fileList = boardService.selectFileList(board.getBno());
		model.addAttribute("file", fileList);
		
		return "board/boardView";
	}
	
	/* 게시글 수정(이동) */
	@RequestMapping(value = "/boardUpdate", method = RequestMethod.GET)
	public String boardUpdate(BoardVO board, Model model,  @ModelAttribute("scri") SearchCriteria scri) throws Exception {
		
		logger.info("게시판 수정 페이지 이동");
		
		model.addAttribute("update", boardService.boardView(board.getBno()));
		model.addAttribute("scri", scri);
		
		List<Map<String, Object>> fileList = boardService.selectFileList(board.getBno());
		model.addAttribute("file", fileList);
		return "board/boardUpdate";	
	}
	
	/* 게시글 수정(저장) */
	@RequestMapping(value = "/boardUpdate", method = RequestMethod.POST)
	public String saveUpdate(BoardVO board,  @ModelAttribute("scri") SearchCriteria scri, RedirectAttributes rttr, 
			@RequestParam(value="fileNoDel[]") String[] files,
			 @RequestParam(value="fileNameDel[]") String[] fileNames, MultipartHttpServletRequest mpRequest) throws Exception {
		
		logger.info("게시판 수정 저장");
		
		boardService.updateBoard(board, files, fileNames, mpRequest);
		
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		
		return "redirect:/board/boardView?bno="+board.getBno();
	}

	/* 게시글 삭제 */
	@RequestMapping(value = "/boardDelete", method = RequestMethod.GET)
	public String boardDelete(RedirectAttributes rttr,  @ModelAttribute("scri") SearchCriteria scri, BoardVO board) throws Exception {
		
		logger.info("게시판 삭제");
		
		boardService.deleteBoard(board.getBno());
		
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		
		return "redirect:/board/boardList";
	}
	
	/* 파일 업로드 */
	@RequestMapping(value="/fileDown")
	public void fileDown(@RequestParam Map<String, Object> map, HttpServletResponse response) throws Exception{
		
		logger.info("파일 업로드 진행");
		
		Map<String, Object> resultMap = boardService.selectFileInfo(map);
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
            String fileUrl = "/board/ckImgSubmit?uid=" + uid + "&fileName=" + fileName;  // 작성화면
            
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
	 public void replyWrite(BoardReplyVO reply, HttpSession session) throws Exception {
	  logger.info("regist reply");
	  
	  MemberVO member = (MemberVO)session.getAttribute("member");
	  reply.setUserid(member.getUserid());
	  
	  replyService.replyWrite(reply);
	  
	 }
	 
	 @ResponseBody
	 @RequestMapping(value = "/replyList", method = RequestMethod.GET)
	 public List<BoardReplyVO> getReplyList(@RequestParam("bno") int bno) throws Exception {
	  logger.info("get reply list");
	    
	  List<BoardReplyVO> reply = replyService.replyList(bno);
	  
	  return reply;
	 } 
	 
	 @ResponseBody
	 @RequestMapping(value = "/deleteReply", method = RequestMethod.POST)
	 public int getDeleteReply(BoardReplyVO reply,  HttpSession session) throws Exception {
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
	 public int modifyReply(BoardReplyVO reply, HttpSession session) throws Exception {
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


	

