package com.nt.ms;

import java.util.List;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.model.SearchInputs;
import com.nt.model.SearchResults;
import com.nt.service.ICourseMgmtService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/reporting/api")
@OpenAPIDefinition (info =
@Info(
          title = "Reporting API",
          version = "1.0",
          description = "Reporting API supporting File Download operations",
          license = @License(name = "Naresh IT", url = "http://nareshit.com"),
          contact = @Contact(url = "http://gigantic-server.com", name = "Fred", email = "Fred@gigagantic-server.com")
  )
)
public class CoursesReportOperationsController {

	@Autowired
	private ICourseMgmtService courseService;

	@GetMapping("/courses")
	@Operation(summary = "Get Courses Information",
    responses = {
            @ApiResponse(description = "CourseInfo",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation =  String.class))),
            @ApiResponse(responseCode = "500", description = "Wrong Url")})
	public ResponseEntity<?> fetchCourseCategory() {
		try {
			// use service class
			Set<String> coursesInfo = courseService.showAllCourseCategories();

			return new ResponseEntity<Set<String>>(coursesInfo, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/training-modes")
	public ResponseEntity<?> fetchTrainingModes() {
		try {
			// use service class
			Set<String> trainingModeInfo = courseService.showAllTrainingMode();

			return new ResponseEntity<Set<String>>(trainingModeInfo, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/faculties")
	public ResponseEntity<?> fetchFaculties() {
		try {
			// use service class
			Set<String> faciltiesInfo = courseService.showAllFaculties();

			return new ResponseEntity<Set<String>>(faciltiesInfo, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/search")
	public ResponseEntity<?> fetchCoursesByFilters(@RequestBody SearchInputs inputs) {
		try {
			List<SearchResults> list = courseService.showCoursesByFilters(inputs);
			return new ResponseEntity<List<SearchResults>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/pdf-report")
	public void showPdfReport(@RequestBody SearchInputs inputs, HttpServletResponse res) {
		try {

			// set the response content type
			res.setContentType("application/pdf");
			// set the content-disposition header to response content going to browser as
			// downloadable file
			res.setHeader("Content-Disposition", "attachment;fileName=courses.pdf ");
			// use service
			courseService.generatePdfReport(inputs, res);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@PostMapping("/excel-report")
	public void showExcelReport(@RequestBody SearchInputs inputs, HttpServletResponse res) {
		try {

			// set the response content type
			res.setContentType("application/vnd.ms-excel");
			// set the content-disposition header to response content going to browser as
			// downloadable file
			res.setHeader("Content-Disposition", "attachment;fileName=courses.xls ");
			// use service
			courseService.generateExcelReport(inputs, res);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@GetMapping("/all-pdf-report")
	public void showPdfReportAllData( HttpServletResponse res) {
		try {

			// set the response content type
			res.setContentType("application/pdf");
			// set the content-disposition header to response content going to browser as
			// downloadable file
			res.setHeader("Content-Disposition", "attachment;fileName=courses.pdf ");
			// use service
			courseService.generatePdfReportAllData(res);
;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@GetMapping("/all-excel-report")
	public void showExcelReportAllData( HttpServletResponse res) {
		try {

			// set the response content type
			res.setContentType("application/vnd.ms-excel");
			// set the content-disposition header to response content going to browser as
			// downloadable file
			res.setHeader("Content-Disposition", "attachment;fileName=courses.xls ");
			// use service
			courseService.generateExcelReportAllData(res);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
