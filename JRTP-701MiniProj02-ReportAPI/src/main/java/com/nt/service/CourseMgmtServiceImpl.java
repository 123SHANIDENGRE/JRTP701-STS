package com.nt.service;

import java.awt.Color;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.nt.entity.CourseDetails;
import com.nt.model.SearchInputs;
import com.nt.model.SearchResults;
import com.nt.repository.ICourseDetailsRepository;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class CourseMgmtServiceImpl implements ICourseMgmtService {

	@Autowired
	private ICourseDetailsRepository courseRepo;
	
	@Override
	public Set<String> showAllCourseCategories() {
		return 	courseRepo.getUniqueCourseCategories();
	}

	@Override
	public Set<String> showAllTrainingMode() {
		return courseRepo.getUniqueTrainingMode();
	}

	@Override
	public Set<String> showAllFaculties() {
		return courseRepo.getUniqueFacultyName();
	}

	/*@Override
	public List<SearchResults> showCoursesByFilters(SearchInputs inputs) {
		
		//get  nonnull and non empty String values from the inputs object and prepare Entity 
		// obj having that non null data and also place that entity object inside Example obj
		
		CourseDetails  entity= new CourseDetails();
		String category=inputs.getCourseCategories();
		if(category!=null&&!category.equals("")&&category.length()!=0)
		{
			entity.setCourseCategory(category);
		}
		
		String facultyName=inputs.getFacultyName();
		if(facultyName!=null&&!facultyName.equals("")&&facultyName.length()!=0)
		{
			entity.setFacultyName(facultyName);
		}
		
		String  trainingMode=inputs.getFacultyName();
		if(trainingMode!=null&&!trainingMode.equals("")&&trainingMode.length()!=0)
		{
			entity.setFacultyName(facultyName);
		}
		LocalDateTime startdate=inputs.getStartsOn();
		if(startdate!=null)
		{
			entity.setStartDate(startdate);
		}
		
		Example example=Example.of(entity);
		
		
		//perform the search operation with Filters data of the Example Entity obj
		List<CourseDetails>  listEntities =	courseRepo.findAll(example);
		//convert List<entity> to List<SerachResult>
 	    List<SearchResults>  listResults=new ArrayList<>();
	    listEntities.forEach(  course->{
	    	SearchResults result =new SearchResults();
	    	BeanUtils.copyProperties(course,result);
	    	listResults.add(result);
	    });
	    return listResults;
 		
	}*/
	
	
  @Override
	public List<SearchResults> showCoursesByFilters(SearchInputs inputs) {
		
		// get nonnull and non empty String values from the inputs object and prepare
		// Entity
		// obj having that non null data and also place that entity object inside
		// Example obj

		CourseDetails entity = new CourseDetails();
		String category = inputs.getCourseCategory();
		if (StringUtils.hasLength(category)) {
			entity.setCourseCategory(category);
		}

		String facultyName = inputs.getFacultyName();
		if (StringUtils.hasLength(facultyName)) {
			entity.setFacultyName(facultyName);
		}

		String trainingMode = inputs.getTrainingMode();
		if (StringUtils.hasLength(trainingMode)) {
			entity.setTrainingMode(trainingMode);
		}
		LocalDateTime startdate = inputs.getStartsOn();
		if(!ObjectUtils.isEmpty(startdate)) {
			entity.setStartDate(startdate);
		}

		Example example = Example.of(entity);

		// perform the search operation with Filters data of the Example Entity obj
		List<CourseDetails> listEntities = courseRepo.findAll(example);
		// convert List<entity> to List<SerachResult>
		List<SearchResults> listResults = new ArrayList<>();
		listEntities.forEach(course -> {
			SearchResults result = new SearchResults();
			BeanUtils.copyProperties(course, result);
			listResults.add(result);
		});
		return listResults;

	}

	@Override
	public void generatePdfReport(SearchInputs inputs, HttpServletResponse res)throws Exception {
	
		//get SearchResult
		List<SearchResults>  listResults=showCoursesByFilters(inputs);
		
		//create Document obj(openpdf)
		Document document=new Document(PageSize.A4);
		
		//get PDfWriter to write to the document and response obj
		PdfWriter.getInstance(document, res.getOutputStream());
		
		
		//open the document
		document.open();
		
		//Define Font for the Paragraph
		Font font=FontFactory.getFont(FontFactory.TIMES_BOLD);
		   font.setSize(30);
		   font.setColor(Color.CYAN);
		   
		   
		   //create the paragraph having content and above font style
		   Paragraph para=new Paragraph("Search Report of Courses", font);
		   para.setAlignment(Paragraph.ALIGN_CENTER);
		   
		   //add paragraph to document 
		   document.add(para);
		   
		
		
		//Display search result as the pdf 	table
		   PdfPTable table=new PdfPTable(10);
		   table.setWidthPercentage( 70);
		   table.setWidths(new float[] {1.0f,1.0f,1.0f,1.0f,1.0f,1.0f,1.0f,1.0f,1.0f,1.0f});
		   table.setSpacingBefore(2.0f);
		   //prepare heading row cells  in the pdf table
		   PdfPCell  cell=new PdfPCell();
		   cell.setBackgroundColor(Color.gray);
		   cell.setPadding(5);
		   
		   Font cellFont=FontFactory.getFont(	FontFactory.HELVETICA_BOLD);
		   cellFont.setColor(Color.BLACK);
		   
		   
		cell.setPhrase(new Phrase("CourseID",cellFont));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("CourseName",cellFont));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Category",cellFont));
		table.addCell(cell);
		
		
		cell.setPhrase(new Phrase("facultyName",cellFont));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Location",cellFont));
		table.addCell(cell);
		
		
		cell.setPhrase(new Phrase("Fee",cellFont));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Course Status",cellFont));
		table.addCell(cell);
		
		
		cell.setPhrase(new Phrase("TrainingMode",cellFont));
		table.addCell(cell);
		   
		
		cell.setPhrase(new Phrase("adminContact",cellFont));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("StartDate",cellFont));
		table.addCell(cell);
		
		
		//add data cells to pdftable
		listResults.forEach(result->{
			 
			 table.addCell(String.valueOf(result.getCourseId()));
			 table.addCell(result.getCourseName());
			 table.addCell(result.getCourseCategory());
			 table.addCell(result.getFacultyName());
			 table.addCell(result.getLocation());
			 table.addCell(String.valueOf(result.getFee()));
			 table.addCell(result.getCourseStatus());
			 table.addCell(result.getTrainingMode());
			 table.addCell(String.valueOf(result.getAdminContact()));
			 table.addCell(result.getStartDate().toString());
			 
			
		});
		//add table to document
		document.add(table);
		
		//close the document
		document.close();
	
		
		   }
		   
		   
	

	@Override
	public void generateExcelReport(SearchInputs inputs, HttpServletResponse res) throws Exception{
		
		//get SearchResult
		List<SearchResults>  listResults=showCoursesByFilters(inputs);
		
		//create ExcelWorkbook(apache poi api)
		HSSFWorkbook workbook=new HSSFWorkbook();
		
		//create sheet in the workbook
		HSSFSheet sheet1=workbook.createSheet("CourseDetails");
		
		//create heading row in sheet1
		HSSFRow headerRow=sheet1.createRow(0);
		headerRow.createCell(0).setCellValue("CourseID");
		headerRow.createCell(1).setCellValue("CourseName");
		headerRow.createCell(2).setCellValue("Location");
		headerRow.createCell(3).setCellValue("CourseCategory");
		headerRow.createCell(4).setCellValue("FacultyName");
		headerRow.createCell(5).setCellValue("fee");
		headerRow.createCell(6).setCellValue("adminContact");
		headerRow.createCell(7).setCellValue("trainingMode");
		headerRow.createCell(8).setCellValue("startDate");
		headerRow.createCell(9).setCellValue("CourseStatus");
		
		//add data rows to the sheet
		int i=1;
	  for(SearchResults   result :listResults)
	  {
		  HSSFRow dataRow=sheet1.createRow(i);
		  dataRow.createCell(0).setCellValue(result.getCourseId());
		  dataRow.createCell(1).setCellValue(result.getCourseName());
		  dataRow.createCell(2).setCellValue(result.getLocation());
		  dataRow.createCell(3).setCellValue(result.getCourseCategory());
		  dataRow.createCell(4).setCellValue(result.getFacultyName());
		  dataRow.createCell(5).setCellValue(result.getFee());
		  dataRow.createCell(6).setCellValue(result.getAdminContact());
		  dataRow.createCell(7).setCellValue(result.getTrainingMode());
		  dataRow.createCell(8).setCellValue(result.getStartDate());
		  dataRow.createCell(9).setCellValue(result.getCourseStatus());
		  i++;
	  }
	  
		//get OutputStream   pointing to response obj
	  ServletOutputStream outputStream=res.getOutputStream();
	  
	  //write the Excel workBook data response Object using the Above stream
	  workbook.write(outputStream);
	  
	  //close the stream
	   outputStream.close();
	   
	   //close the workbook
	   workbook.close();
		
		
	}

	@Override
	public void generatePdfReportAllData(HttpServletResponse res) throws Exception {
		
  //get all the records from Db table
		List<CourseDetails>  list=courseRepo.findAll();
		
		//copy List<CourseDetails> to List<SearchResearch>
		List<SearchResults> listResults=new ArrayList();
		
		
		list.forEach(course->{
			 SearchResults result=new SearchResults();
			 BeanUtils.copyProperties(course, result);
			 listResults.add(result);
		});
		
		
		
		
		
		
		//create Document obj(openpdf)
		Document document=new Document(PageSize.A4);
		
		//get PDfWriter to write to the document and response obj
		PdfWriter.getInstance(document, res.getOutputStream());
		
		
		//open the document
		document.open();
		
		//Define Font for the Paragraph
		Font font=FontFactory.getFont(FontFactory.TIMES_BOLD);
		   font.setSize(30);
		   font.setColor(Color.CYAN);
		   
		   
		   //create the paragraph having content and above font style
		   Paragraph para=new Paragraph("Search Report of Courses", font);
		   para.setAlignment(Paragraph.ALIGN_CENTER);
		   
		   //add paragraph to document 
		   document.add(para);
		   
		
		
		//Display search result as the pdf 	table
		   PdfPTable table=new PdfPTable(10);
		   table.setWidthPercentage( 70);
		   table.setWidths(new float[] {6.0f,6.0f,6.0f,6.0f,6.0f,6.0f,6.0f,6.0f,6.0f,6.0f});
		   table.setSpacingBefore(2.0f);
		   //prepare heading row cells  in the pdf table
		   PdfPCell  cell=new PdfPCell();
		   cell.setBackgroundColor(Color.gray);
		   cell.setPadding(5);
		   
		   Font cellFont=FontFactory.getFont(	FontFactory.HELVETICA_BOLD);
		   cellFont.setColor(Color.BLACK);
		   
		   
		cell.setPhrase(new Phrase("CourseID",cellFont));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("CourseName",cellFont));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Category",cellFont));
		table.addCell(cell);
		
		
		cell.setPhrase(new Phrase("facultyName",cellFont));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Location",cellFont));
		table.addCell(cell);
		
		
		cell.setPhrase(new Phrase("Fee",cellFont));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Course Status",cellFont));
		table.addCell(cell);
		
		
		cell.setPhrase(new Phrase("TrainingMode",cellFont));
		table.addCell(cell);
		   
		
		cell.setPhrase(new Phrase("adminContact",cellFont));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("StartDate",cellFont));
		table.addCell(cell);
		
		
		//add data cells to pdftable
		listResults.forEach(result->{
			 
			 table.addCell(String.valueOf(result.getCourseId()));
			 table.addCell(result.getCourseName());
			 table.addCell(result.getCourseCategory());
			 table.addCell(result.getFacultyName());
			 table.addCell(result.getLocation());
			 table.addCell(String.valueOf(result.getFee()));
			 table.addCell(result.getCourseStatus());
			 table.addCell(result.getTrainingMode());
			 table.addCell(String.valueOf(result.getAdminContact()));
			 table.addCell(result.getStartDate().toString());
			 
			
		});
		//add table to document
		document.add(table);
		
		//close the document
		document.close();
	
		
	}

	@Override
	public void generateExcelReportAllData(HttpServletResponse res) throws Exception {

		
		 //get all the records from Db table
		List<CourseDetails>  list=courseRepo.findAll();
		
		//copy List<CourseDetails> to List<SearchResearch>
		List<SearchResults> listResults=new ArrayList();
		
		
		list.forEach(course->{
			 SearchResults result=new SearchResults();
			 BeanUtils.copyProperties(course, result);
			 listResults.add(result);
		});
		
		
		
		
		//create ExcelWorkbook(apache poi api)
		HSSFWorkbook workbook=new HSSFWorkbook();
		
		//create sheet in the workbook
		HSSFSheet sheet1=workbook.createSheet("CourseDetails");
		
		//create heading row in sheet1
		HSSFRow headerRow=sheet1.createRow(0);
		headerRow.createCell(0).setCellValue("CourseID");
		headerRow.createCell(1).setCellValue("CourseName");
		headerRow.createCell(2).setCellValue("Location");
		headerRow.createCell(3).setCellValue("CourseCategory");
		headerRow.createCell(4).setCellValue("FacultyName");
		headerRow.createCell(5).setCellValue("fee");
		headerRow.createCell(6).setCellValue("adminContact");
		headerRow.createCell(7).setCellValue("trainingMode");
		headerRow.createCell(8).setCellValue("startDate");
		headerRow.createCell(9).setCellValue("CourseStatus");
		
		//add data rows to the sheet
		int i=1;
	  for(SearchResults   result :listResults)
	  {
		  HSSFRow dataRow=sheet1.createRow(i);
		  dataRow.createCell(0).setCellValue(result.getCourseId());
		  dataRow.createCell(1).setCellValue(result.getCourseName());
		  dataRow.createCell(2).setCellValue(result.getLocation());
		  dataRow.createCell(3).setCellValue(result.getCourseCategory());
		  dataRow.createCell(4).setCellValue(result.getFacultyName());
		  dataRow.createCell(5).setCellValue(result.getFee());
		  dataRow.createCell(6).setCellValue(result.getAdminContact());
		  dataRow.createCell(7).setCellValue(result.getTrainingMode());
		  dataRow.createCell(8).setCellValue(result.getStartDate());
		  dataRow.createCell(9).setCellValue(result.getCourseStatus());
		  i++;
	  }
	  
		//get OutputStream   pointing to response obj
	  ServletOutputStream outputStream=res.getOutputStream();
	  
	  //write the Excel workBook data response Object using the Above stream
	  workbook.write(outputStream);
	  
	  //close the stream
	   outputStream.close();
	   
	   //close the workbook
	   workbook.close();
		
		
		
	}

}