package data.dao.mysql;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import data.dao.PdfExporterDAO;
import data.dto.DogDTO;
import data.dto.FosterParentDTO;
import data.dto.LoggerDTO;
import javafx.scene.control.TableView;
import util.AzilUtilities;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;


public class MySQLPdfExporter implements PdfExporterDAO  {

    @Override
    public void exportEmployees() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String Query = "SELECT * FROM zaposleni";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(Query);

            Document pdfReport = new Document();
            PdfWriter.getInstance(pdfReport, new FileOutputStream("reports" + File.separator + "zaposleni.pdf"));
            pdfReport.open();


            Paragraph paragraphAzil = new Paragraph("AzilZaPse - Banja Luka d.o.o");
            paragraphAzil.setAlignment(Element.ALIGN_RIGHT);
            pdfReport.add(paragraphAzil);

            Paragraph paragraphTime = new Paragraph("Vrijeme: " + new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new java.util.Date()));
            paragraphTime.setAlignment(Element.ALIGN_RIGHT);
            pdfReport.add(paragraphTime);

            Image image = Image.getInstance("file:" + "resources" + File.separator + "reportImages" + File.separator + "employee.png");
            image.setBackgroundColor(BaseColor.RED);
            image.setAlignment(Element.ALIGN_CENTER);
            pdfReport.add(image);


            pdfReport.add(new Paragraph("\n\n"));
            pdfReport.add(new Paragraph("Lista svih zaposlenika u azilu:"));
            pdfReport.add(new Paragraph("\n"));


            PdfPTable pdfPTable = new PdfPTable(5);
            pdfPTable.setTotalWidth(500);
            pdfPTable.setWidths(new int[]{70, 130, 100, 100, 100});

            PdfPCell pdfTableCell;

            boolean first = true;
            int i = 0;
            while (rs.next()) {
                if (first) {
                    first = false;
                    pdfTableCell = new PdfPCell(new Phrase("Redni br."));
                    pdfTableCell.setMinimumHeight(25);
                    pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
                    pdfPTable.addCell(pdfTableCell);

                    pdfTableCell = new PdfPCell(new Phrase("JMBG"));
                    pdfTableCell.setMinimumHeight(25);
                    pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
                    pdfPTable.addCell(pdfTableCell);

                    pdfTableCell = new PdfPCell(new Phrase("Ime"));
                    pdfTableCell.setMinimumHeight(25);
                    pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
                    pdfPTable.addCell(pdfTableCell);

                    pdfTableCell = new PdfPCell(new Phrase("Prezime"));
                    pdfTableCell.setMinimumHeight(25);
                    pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
                    pdfPTable.addCell(pdfTableCell);

                    pdfTableCell = new PdfPCell(new Phrase("Username"));
                    pdfTableCell.setMinimumHeight(25);
                    pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
                    pdfPTable.addCell(pdfTableCell);
                }
                pdfTableCell = new PdfPCell(new Phrase(++i + "."));
                pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setMinimumHeight(20);
                pdfPTable.addCell(pdfTableCell);

                pdfTableCell = new PdfPCell(new Phrase(rs.getString("JMBG")));
                pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setMinimumHeight(20);
                pdfPTable.addCell(pdfTableCell);

                pdfTableCell = new PdfPCell(new Phrase(rs.getString("Ime")));
                pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setMinimumHeight(20);
                pdfPTable.addCell(pdfTableCell);

                pdfTableCell = new PdfPCell(new Phrase(rs.getString("Prezime")));
                pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setMinimumHeight(20);
                pdfPTable.addCell(pdfTableCell);

                pdfTableCell = new PdfPCell(new Phrase(rs.getString("Username")));
                pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setMinimumHeight(20);
                pdfPTable.addCell(pdfTableCell);
            }

            pdfReport.add(pdfPTable);

            pdfReport.close();

            rs.close();
            statement.close();
            conn.close();

        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLPdfExporter - exportEmployees", ex.fillInStackTrace().toString()));
        }
    }

    @Override
    public void exportMedicine() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String Query = "SELECT * FROM lijek";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(Query);

            Document pdfReport = new Document();
            PdfWriter.getInstance(pdfReport, new FileOutputStream("reports" + File.separator + "lijekovi.pdf"));
            pdfReport.open();


            Paragraph paragraphAzil = new Paragraph("AzilZaPse - Banja Luka d.o.o");
            paragraphAzil.setAlignment(Element.ALIGN_RIGHT);
            pdfReport.add(paragraphAzil);

            Paragraph paragraphTime = new Paragraph("Vrijeme: " + new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new java.util.Date()));
            paragraphTime.setAlignment(Element.ALIGN_RIGHT);
            pdfReport.add(paragraphTime);

            Image image = Image.getInstance("file:" + "resources" + File.separator + "reportImages" + File.separator + "medicine.png");
            image.setBackgroundColor(BaseColor.RED);
            image.setAlignment(Element.ALIGN_CENTER);
            pdfReport.add(image);


            pdfReport.add(new Paragraph("\n\n"));
            pdfReport.add(new Paragraph("Lista svih lijekova u azilu:"));
            pdfReport.add(new Paragraph("\n"));


            PdfPTable pdfPTable = new PdfPTable(3);
            // pdfPTable.setTotalWidth(500);
            // pdfPTable.setWidths(new int[]{70, 130, 100, 100, 100});

            PdfPCell pdfTableCell;

            boolean first = true;
            int i = 0;
            while (rs.next()) {
                if (first) {
                    first = false;
                    pdfTableCell = new PdfPCell(new Phrase("Redni br."));
                    pdfTableCell.setMinimumHeight(25);
                    pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
                    pdfPTable.addCell(pdfTableCell);

                    pdfTableCell = new PdfPCell(new Phrase("Naziv"));
                    pdfTableCell.setMinimumHeight(25);
                    pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
                    pdfPTable.addCell(pdfTableCell);

                    pdfTableCell = new PdfPCell(new Phrase("Stanje/Kolicina"));
                    pdfTableCell.setMinimumHeight(25);
                    pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
                    pdfPTable.addCell(pdfTableCell);
                }
                pdfTableCell = new PdfPCell(new Phrase(++i + "."));
                pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setMinimumHeight(20);
                pdfPTable.addCell(pdfTableCell);

                pdfTableCell = new PdfPCell(new Phrase(rs.getString("NazivLijeka")));
                pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setMinimumHeight(20);
                pdfPTable.addCell(pdfTableCell);

                pdfTableCell = new PdfPCell(new Phrase(rs.getString("Kolicina")));
                pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setMinimumHeight(20);
                pdfPTable.addCell(pdfTableCell);
            }

            pdfReport.add(pdfPTable);

            pdfReport.close();

            rs.close();
            statement.close();
            conn.close();

        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLPdfExporter - exportMedicine", ex.fillInStackTrace().toString()));
        }
    }

    @Override
    public void exportMedicalReports() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String QueryNew = "SELECT * FROM nalaz n " +
                       "JOIN veterinar v ON n.VeterinarJMBG = v.VeterinarJMBG " +
                       "JOIN pas p ON n.PasID = p.PasID";

        String Query = "SELECT z.Ime ImeVeterinara, n.DatumPregleda DatumPregleda, p.Ime Pas, n.Dijagnoza Dijagnoza FROM nalaz n " +
                "JOIN veterinar v ON n.Veterinar_Zaposleni_JMBG = v.Zaposleni_JMBG " +
                "JOIN zaposleni z ON v.Zaposleni_JMBG = z.JMBG " +
                "JOIN pas p ON n.Pas_IdPsa = p.IdPsa";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(Query);

            Document pdfReport = new Document();
            PdfWriter.getInstance(pdfReport, new FileOutputStream("reports" + File.separator + "nalazi.pdf"));
            pdfReport.open();


            Paragraph paragraphAzil = new Paragraph("AzilZaPse - Banja Luka d.o.o");
            paragraphAzil.setAlignment(Element.ALIGN_RIGHT);
            pdfReport.add(paragraphAzil);

            Paragraph paragraphTime = new Paragraph("Vrijeme: " + new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new java.util.Date()));
            paragraphTime.setAlignment(Element.ALIGN_RIGHT);
            pdfReport.add(paragraphTime);

            Image image = Image.getInstance("file:" + "resources" + File.separator + "reportImages" + File.separator + "medical-report.png");
            image.setBackgroundColor(BaseColor.RED);
            image.setAlignment(Element.ALIGN_CENTER);
            pdfReport.add(image);


            pdfReport.add(new Paragraph("\n\n"));
            pdfReport.add(new Paragraph("Lista svih nalaza u azilu:"));
            pdfReport.add(new Paragraph("\n"));


            PdfPTable pdfPTable = new PdfPTable(5);
            // pdfPTable.setTotalWidth(500);
            // pdfPTable.setWidths(new int[]{70, 130, 100, 100, 100});

            PdfPCell pdfTableCell;

            boolean first = true;
            int i = 0;
            while (rs.next()) {
                if (first) {
                    first = false;
                    pdfTableCell = new PdfPCell(new Phrase("Redni br."));
                    pdfTableCell.setMinimumHeight(25);
                    pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
                    pdfPTable.addCell(pdfTableCell);

                    pdfTableCell = new PdfPCell(new Phrase("Ime veterinara"));
                    pdfTableCell.setMinimumHeight(25);
                    pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
                    pdfPTable.addCell(pdfTableCell);

                    pdfTableCell = new PdfPCell(new Phrase("Datum Pregleda"));
                    pdfTableCell.setMinimumHeight(25);
                    pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
                    pdfPTable.addCell(pdfTableCell);

                    pdfTableCell = new PdfPCell(new Phrase("Pas"));
                    pdfTableCell.setMinimumHeight(25);
                    pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
                    pdfPTable.addCell(pdfTableCell);

                    pdfTableCell = new PdfPCell(new Phrase("Dijagnoza"));
                    pdfTableCell.setMinimumHeight(25);
                    pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
                    pdfPTable.addCell(pdfTableCell);
                }
                pdfTableCell = new PdfPCell(new Phrase(++i + "."));
                pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setMinimumHeight(20);
                pdfPTable.addCell(pdfTableCell);

                pdfTableCell = new PdfPCell(new Phrase(rs.getString("ImeVeterinara")));
                pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setMinimumHeight(20);
                pdfPTable.addCell(pdfTableCell);

                pdfTableCell = new PdfPCell(new Phrase(rs.getString("DatumPregleda")));
                pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setMinimumHeight(20);
                pdfPTable.addCell(pdfTableCell);

                pdfTableCell = new PdfPCell(new Phrase(rs.getString("Pas")));
                pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setMinimumHeight(20);
                pdfPTable.addCell(pdfTableCell);

                pdfTableCell = new PdfPCell(new Phrase(rs.getString("Dijagnoza")));
                pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setMinimumHeight(20);
                pdfPTable.addCell(pdfTableCell);
            }

            pdfReport.add(pdfPTable);

            pdfReport.close();

            rs.close();
            statement.close();
            conn.close();

        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLPdfExporter - exportMedicalReports", ex.fillInStackTrace().toString()));
        }
    }

    @Override
    public void exportFosters(TableView<FosterParentDTO> fosterParentsTableView) {
        try {
            Document pdfReport = new Document();
            PdfWriter.getInstance(pdfReport, new FileOutputStream("reports" + File.separator + "udomitelji.pdf"));
            pdfReport.open();


            Paragraph paragraphAzil = new Paragraph("AzilZaPse - Banja Luka d.o.o");
            paragraphAzil.setAlignment(Element.ALIGN_RIGHT);
            pdfReport.add(paragraphAzil);

            Paragraph paragraphTime = new Paragraph("Vrijeme: " + new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new java.util.Date()));
            paragraphTime.setAlignment(Element.ALIGN_RIGHT);
            pdfReport.add(paragraphTime);

            Image image = Image.getInstance("file:" + "resources" + File.separator + "reportImages" + File.separator + "fosters.png");
            image.setBackgroundColor(BaseColor.RED);
            image.setAlignment(Element.ALIGN_CENTER);
            pdfReport.add(image);


            pdfReport.add(new Paragraph("\n\n"));
            pdfReport.add(new Paragraph("Lista svih udomitelja u azilu:"));
            pdfReport.add(new Paragraph("\n"));


            PdfPTable pdfPTable = new PdfPTable(5);
            pdfPTable.setTotalWidth(675);
            pdfPTable.setWidths(new int[]{75, 150, 150, 150, 150});

            PdfPCell pdfTableCell;


            pdfTableCell = new PdfPCell(new Phrase("Redni br."));
            pdfTableCell.setMinimumHeight(25);
            pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
            pdfPTable.addCell(pdfTableCell);

            pdfTableCell = new PdfPCell(new Phrase("Ime"));
            pdfTableCell.setMinimumHeight(25);
            pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
            pdfPTable.addCell(pdfTableCell);

            pdfTableCell = new PdfPCell(new Phrase("Prezime"));
            pdfTableCell.setMinimumHeight(25);
            pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
            pdfPTable.addCell(pdfTableCell);

            pdfTableCell = new PdfPCell(new Phrase("Prebivaliste"));
            pdfTableCell.setMinimumHeight(25);
            pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
            pdfPTable.addCell(pdfTableCell);

            pdfTableCell = new PdfPCell(new Phrase("Broj Telefona"));
            pdfTableCell.setMinimumHeight(25);
            pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
            pdfPTable.addCell(pdfTableCell);

            for (int i = 0; i < fosterParentsTableView.getItems().size(); i++) {
                pdfTableCell = new PdfPCell(new Phrase((i + 1) + "."));
                pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setMinimumHeight(20);
                pdfPTable.addCell(pdfTableCell);

                pdfTableCell = new PdfPCell(new Phrase(fosterParentsTableView.getItems().get(i).getName()));
                pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setMinimumHeight(20);
                pdfPTable.addCell(pdfTableCell);

                pdfTableCell = new PdfPCell(new Phrase(fosterParentsTableView.getItems().get(i).getSurname()));
                pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setMinimumHeight(20);
                pdfPTable.addCell(pdfTableCell);

                pdfTableCell = new PdfPCell(new Phrase(fosterParentsTableView.getItems().get(i).getResidenceAddress()));
                pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setMinimumHeight(20);
                pdfPTable.addCell(pdfTableCell);

                pdfTableCell = new PdfPCell(new Phrase(fosterParentsTableView.getItems().get(i).getTelephoneNumber()));
                pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setMinimumHeight(20);
                pdfPTable.addCell(pdfTableCell);
            }

            pdfReport.add(pdfPTable);

            pdfReport.close();
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLPdfExporter - exportFosters", ex.fillInStackTrace().toString()));
        }
    }

    @Override
    public void exportAdoptedDogs(TableView<DogDTO> dogsTableView) {
        try {
            Document pdfReport = new Document();
            PdfWriter.getInstance(pdfReport, new FileOutputStream("reports" + File.separator + "udomljeniPsi.pdf"));
            pdfReport.open();


            Paragraph paragraphAzil = new Paragraph("AzilZaPse - Banja Luka d.o.o");
            paragraphAzil.setAlignment(Element.ALIGN_RIGHT);
            pdfReport.add(paragraphAzil);

            Paragraph paragraphTime = new Paragraph("Vrijeme: " + new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new java.util.Date()));
            paragraphTime.setAlignment(Element.ALIGN_RIGHT);
            pdfReport.add(paragraphTime);

            Image image = Image.getInstance("file:" + "resources" + File.separator + "reportImages" + File.separator + "dog.png");
            image.setBackgroundColor(BaseColor.RED);
            image.setAlignment(Element.ALIGN_CENTER);
            pdfReport.add(image);


            pdfReport.add(new Paragraph("\n\n"));
            pdfReport.add(new Paragraph("Lista svih udomljenih pasa u azilu:"));
            pdfReport.add(new Paragraph("\n"));


            PdfPTable pdfPTable = new PdfPTable(7);
            //pdfPTable.setWidthPercentage(new float[] {10, });
            //pdfPTable.setTotalWidth(675);
            //pdfPTable.setWidths(new int[]{75, 150, 150, 150, 150});

            PdfPCell pdfTableCell;


            pdfTableCell = new PdfPCell(new Phrase("Redni br."));
            pdfTableCell.setMinimumHeight(25);
            pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
            pdfPTable.addCell(pdfTableCell);

            pdfTableCell = new PdfPCell(new Phrase("Ime"));
            pdfTableCell.setMinimumHeight(25);
            pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
            pdfPTable.addCell(pdfTableCell);

            pdfTableCell = new PdfPCell(new Phrase("Rasa"));
            pdfTableCell.setMinimumHeight(25);
            pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
            pdfPTable.addCell(pdfTableCell);

            pdfTableCell = new PdfPCell(new Phrase("Pol"));
            pdfTableCell.setMinimumHeight(25);
            pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
            pdfPTable.addCell(pdfTableCell);

            pdfTableCell = new PdfPCell(new Phrase("Visina"));
            pdfTableCell.setMinimumHeight(25);
            pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
            pdfPTable.addCell(pdfTableCell);

            pdfTableCell = new PdfPCell(new Phrase("Tezina"));
            pdfTableCell.setMinimumHeight(25);
            pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
            pdfPTable.addCell(pdfTableCell);

            pdfTableCell = new PdfPCell(new Phrase("Datum Rodjenja"));
            pdfTableCell.setMinimumHeight(25);
            pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
            pdfPTable.addCell(pdfTableCell);

            for (int i = 0; i < dogsTableView.getItems().size(); i++) {
                pdfTableCell = new PdfPCell(new Phrase((i + 1) + "."));
                pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setMinimumHeight(20);
                pdfPTable.addCell(pdfTableCell);

                pdfTableCell = new PdfPCell(new Phrase(dogsTableView.getItems().get(i).getName()));
                pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setMinimumHeight(20);
                pdfPTable.addCell(pdfTableCell);

                pdfTableCell = new PdfPCell(new Phrase(dogsTableView.getItems().get(i).getBreed()));
                pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setMinimumHeight(20);
                pdfPTable.addCell(pdfTableCell);

                pdfTableCell = new PdfPCell(new Phrase(dogsTableView.getItems().get(i).getGender()));
                pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setMinimumHeight(20);
                pdfPTable.addCell(pdfTableCell);

                pdfTableCell = new PdfPCell(new Phrase(Integer.toString(dogsTableView.getItems().get(i).getHeight())));
                pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setMinimumHeight(20);
                pdfPTable.addCell(pdfTableCell);

                pdfTableCell = new PdfPCell(new Phrase(Double.toString(dogsTableView.getItems().get(i).getWeight())));
                pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setMinimumHeight(20);
                pdfPTable.addCell(pdfTableCell);

                pdfTableCell = new PdfPCell(new Phrase(dogsTableView.getItems().get(i).getDateOfBirth().toString()));
                pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setMinimumHeight(20);
                pdfPTable.addCell(pdfTableCell);
            }

            pdfReport.add(pdfPTable);

            pdfReport.close();
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLPdfExporter - exportAdoptedDogs", ex.fillInStackTrace().toString()));
        }
    }

}
