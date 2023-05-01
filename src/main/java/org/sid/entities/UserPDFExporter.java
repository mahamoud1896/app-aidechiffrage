package org.sid.entities;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

//import com.aspose.pdf.*;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class UserPDFExporter {
	
	 
	
	//Aspose.Pdf.Table table = new Aspose.Pdf.Table();

	
	//private List<Profil> lisProfils;
	
	private List<FicheProjet> lisFicheProjets;
	
	

	public UserPDFExporter(List<FicheProjet> lisFicheProjets) {
		super();
		this.lisFicheProjets = lisFicheProjets;
	}



	/*public UserPDFExporter(List<Profil> lisProfils) {
		super();
		this.lisProfils = lisProfils;
	} */
	
	
	
	private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
       // table.setHeaderRows(11);
       
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        
        cell.setPhrase(new Phrase("Nom du projet", font));
        table.addCell(cell);
        
        //cell.setPhrase(new Phrase("Nom du client", font));
        //table.addCell(cell);
        //cell.setColumn(new ColumnText(2L));
        
        cell.setPhrase(new Phrase("Nombre d'employés ayant participé à la mission",
        		font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Roles dans le projet", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Description Synthetique de la solution", font));
        table.addCell(cell);
         
		/*
		 * cell.setPhrase(new Phrase("Date de début prévisionnelle", font));
		 * table.addCell(cell);
		 * 
		 * cell.setPhrase(new Phrase("Date de fin prévisionnelle", font));
		 * table.addCell(cell);
		 * 
		 * cell.setPhrase(new Phrase("Date de début effective", font));
		 * table.addCell(cell);
		 * 
		 * cell.setPhrase(new Phrase("Date de fin effective", font));
		 * table.addCell(cell);
		 */
       
         
              
    }
     
    private void writeTableData(PdfPTable table) {
        for (FicheProjet profil : lisFicheProjets) {
            
        	//table.setHeaderRows(11);
            
        	//table.addCell(profil.getTypeProjet().toString());
            //table.addCell(String.valueOf(profil.getNumProjet()));
            table.addCell(profil.getNomProjet());
           // table.addCell(profil.getClient().getNomClient());
            
            table.addCell(String.valueOf(profil.getNombreRess()));
            
            table.addCell(profil.getActivites().toString());
            table.addCell(profil.getDescSynthetique());
			/*
			 * table.addCell(profil.getDateDebutPrev().toGMTString());
			 * table.addCell(profil.getDateFinPrev().toGMTString());
			 * table.addCell(profil.getDateDebutEffective().toGMTString());
			 * table.addCell(profil.getDateFinEffective().toGMTString());
			 */
            
            //table.addCell(String.valueOf(user.isEnabled()));
        }
    }
	
	
	public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
         
        Paragraph p = new Paragraph("Fiche de référence", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
         
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.0f, 3.5f,4.5f});
        table.setSpacingBefore(10);
         
        writeTableHeader(table);
        writeTableData(table);
         
        document.add(table);
         
        document.close();
         
    }
	
	
//	public static void AddTable_RowColSpan() {
//        // Load source PDF document
//        Document pdfDocument = new Document();
//        Page page = pdfDocument.getPages().add();
//
//        // Initializes a new instance of the Table
//        Table table = new Table();
//        // Set the table border color as LightGray
//        table.setBorder(new BorderInfo(BorderSide.All, .5f, Color.getBlack()));
//        // Set the border for table cells
//        table.setDefaultCellBorder(new BorderInfo(BorderSide.All, .5f, Color.getBlack()));
//        // Add 1st row to table
//        Row row1 = table.getRows().add();
//        for (int cellCount = 1; cellCount < 5; cellCount++) {
//            // Add table cells
//            row1.getCells().add("Test 1 " + cellCount);
//        }
//
//        // Add 2nd row to table
//        Row row2 = table.getRows().add();
//        row2.getCells().add("Test 2 1");
//        Cell cell = row2.getCells().add("Test 2 2");
//        cell.setColSpan(2);
//        row2.getCells().add("Test 2 4");
//
//        // Add 3rd row to table
//        Row row3 = table.getRows().add();
//        row3.getCells().add("Test 3 1");
//        row3.getCells().add("Test 3 2");
//        row3.getCells().add("Test 3 3");
//        row3.getCells().add("Test 3 4");
//
//        // Add 4th row to table
//        Row row4 = table.getRows().add();
//        row3.getCells().add("Test 4 1");
//        cell = row3.getCells().add("Test 4 2");
//        cell.setRowSpan(2);
//        row3.getCells().add("Test 4 3");
//        row3.getCells().add("Test 4 4");
//
//        // Add 5th row to table
//        row4 = table.getRows().add();
//        row4.getCells().add("Test 5 1");
//        row4.getCells().add("Test 5 3");
//        row4.getCells().add("Test 5 4");
//
//        // Add table object to first page of input document
//        page.getParagraphs().add(table);
//
//        // Save updated document containing table object
//        pdfDocument.save(_dataDir + "document_with_table_out.pdf");
//    }
	
	

	

}
