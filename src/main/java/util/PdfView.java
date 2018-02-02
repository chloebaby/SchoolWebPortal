package util;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.Course;
import model.Result;
import model.Semester;
import model.Student;

public class PdfView extends AbstractITextPdfView {
	
	public PdfView() {}
	
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception{
		Student student = (Student) model.get(Constants.PDF_COMMAND);
		
		Paragraph paragraph = new Paragraph(10, "Results for " + student.getFirstName() + " " + student.getLastName());
		paragraph.setSpacingAfter(20f);
		
		document.add(paragraph);
		
		PdfPTable table = new PdfPTable(4);
		table.setWidthPercentage(100.0f);
		table.setWidths(new float[] {2.0f, 2.0f, 2.0f, 2.0f});
		table.setSpacingAfter(10);
		
		
		
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(BaseColor.WHITE);
		
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor((BaseColor.BLUE));
		cell.setPadding(5);
		
		cell.setPhrase(new Phrase("Course Code", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Course Name", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Result %", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Semester", font));
		table.addCell(cell);
		
		Set<Result> studentResults = student.getListOfResults();
		
		Iterator<Result> studentResultsIterator = studentResults.iterator();
		
		while(studentResultsIterator.hasNext()) {
			Result result = studentResultsIterator.next();
			
			table.addCell(result.getCourse().getCourseCode());
			table.addCell(result.getCourse().getCourseName());
			table.addCell(String.valueOf(result.getMarks()));
			
			Set<Semester> resultSemesters = result.getListOfSemesters();
			Iterator<Semester> resultSemestersIterator = resultSemesters.iterator();
			
			UUID resultId = result.getResultId();
			
			while(resultSemestersIterator.hasNext()) {
				Semester semester = resultSemestersIterator.next();
				
				Set<Result> semesterResults = semester.getListOfResults();
				Iterator<Result> semesterResultsIterator = semesterResults.iterator();
				
				while(semesterResultsIterator.hasNext()) {
					Result semResult = semesterResultsIterator.next();
					UUID semResultId = semResult.getResultId();
					if(semResultId.equals(resultId)) {
						table.addCell(semester.getSemester());
					}
				}
			}
		
		}
		
		document.add(table);
		
	}

}
