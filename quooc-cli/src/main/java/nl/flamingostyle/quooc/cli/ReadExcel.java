package nl.flamingostyle.quooc.cli;

import java.io.File;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadExcel {

	public static void read(File file) {

		// Use a file
		try {
			Workbook wb = WorkbookFactory.create(file);
			Sheet sheet = wb.getSheetAt(0);
			int rowNumber = 1;
			for (Row row : sheet) {
				if (rowNumber == 1) {
					rowNumber++;
					continue;
				}
				Cell artistCell = row.getCell(0);
				Cell titleCell = row.getCell(1);
				Cell firstnameCell = row.getCell(2);
				String background = new String();
				if(row.getCell(3) != null){
					Cell backgroundCell = row.getCell(3);
					background = backgroundCell.getStringCellValue();
				}
				String reporter = new String("Remco");
				if(row.getCell(4) != null){
					Cell reporterCell = row.getCell(3);
					reporter = reporterCell.getStringCellValue();
				}

				
				String artist = artistCell.getStringCellValue();
				String title = titleCell.getStringCellValue();
				String firstname = firstnameCell.getStringCellValue();
				int nameLength = firstname.length();
				int nameIndex = title.indexOf(firstname);

				Song song = new Song();
				song.setArtist(artist.replaceAll("'", "''"));
				song.setTitle(title.replaceAll("'", "''"));
				song.setFirstname(firstname.replaceAll("'", "''"));
				song.setNameIndex(nameIndex);
				song.setNameLength(nameLength);
				song.setBackground(background);
				song.setReporter(reporter);
				
				DatabaseUtil.write(song);
				
				rowNumber++;
			}
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
