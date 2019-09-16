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
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MySQLPdfExporterDAO implements PdfExporterDAO  {

    @Override
    public void exportEmployees(String condition) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String Query = "";
        String Title = "";
        if (condition.equals("SVI")) {
            Query = "SELECT JMBG, Ime, Prezime, Pozicija " +
                    "FROM zaposleni z " +
                    "JOIN zaposleni_ugovor zu " +
                    "ON z.JMBG = zu.ZaposlenikJMBG " +
                    "JOIN ugovororadu uor " +
                    "ON zu.IdUgovora = uor.IdUgovora";
            Title = "Spisak svih zaposlenika u azilu: ";
        }
        else if (condition.equals("AKTIVNI")) {
            Query = "SELECT JMBG, Ime, Prezime, Pozicija " +
                    "FROM zaposleni z " +
                    "JOIN zaposleni_ugovor zu " +
                    "ON z.JMBG = zu.ZaposlenikJMBG " +
                    "JOIN ugovororadu uor " +
                    "ON zu.IdUgovora = uor.IdUgovora " +
                    "WHERE Aktivan = 1";
            Title = "Spisak svih zaposlenika koji trenutno rade u azilu: ";
        }
        else if (condition.equals("NEAKTIVNI")) {
            Query = "SELECT JMBG, Ime, Prezime, Pozicija " +
                    "FROM zaposleni z " +
                    "JOIN zaposleni_ugovor zu " +
                    "ON z.JMBG = zu.ZaposlenikJMBG " +
                    "JOIN ugovororadu uor " +
                    "ON zu.IdUgovora = uor.IdUgovora " +
                    "WHERE Aktivan = 0";
            Title = "Spisak svih neaktivnih zaposlenika u azilu: ";
        }
        try {
            conn = ConnectionPool.getInstance().checkOut();
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(Query);

            Document pdfReport = new Document();
            PdfWriter.getInstance(pdfReport, new FileOutputStream("reports" + File.separator + "Izvjestaj_zaposleni_" + condition + "_" + new SimpleDateFormat("dd-MM-yyyy_HH-mm").format(new java.util.Date()) + ".pdf"));
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
            pdfReport.add(new Paragraph(Title));
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

                    pdfTableCell = new PdfPCell(new Phrase("Pozicija"));
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

                pdfTableCell = new PdfPCell(new Phrase(rs.getString("Pozicija")));
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
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLPdfExporter - exportEmployees", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
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
            PdfWriter.getInstance(pdfReport, new FileOutputStream("reports" + File.separator + "Izvjestaj_lijekovi_" + new SimpleDateFormat("dd-MM-yyyy_HH-mm").format(new java.util.Date()) + ".pdf"));
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

                pdfTableCell = new PdfPCell(new Phrase(rs.getString("Naziv")));
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
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLPdfExporter - exportMedicine", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    @Override
    public void exportMedicalReports() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String Query = "SELECT z.Ime ImeVeterinara, n.DatumPregleda DatumPregleda, p.Ime Pas, n.Dijagnoza Dijagnoza FROM nalaz n " +
                       "JOIN veterinar v ON n.VeterinarJMBG = v.VeterinarJMBG " +
                       "JOIN zaposleni z ON v.VeterinarJMBG = z.JMBG " +
                       "JOIN pas p ON n.IdPsa = p.IdPsa";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(Query);

            Document pdfReport = new Document();
            PdfWriter.getInstance(pdfReport, new FileOutputStream("reports" + File.separator + "Izvjestaj_nalazi_" + new SimpleDateFormat("dd-MM-yyyy_HH-mm").format(new java.util.Date()) + ".pdf"));
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
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLPdfExporter - exportMedicalReports", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    @Override
    public void exportCages() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String Query = "SELECT k.Naziv, k.Kapacitet, k.Kapacitet - (SELECT count(*) FROM kavez k2 JOIN kavez_pas kp2 " +
                                                                   "ON k2.IdKaveza = kp2.IdKaveza " +
                                                                   "WHERE k2.IdKaveza = k.IdKaveza) Popunjenost " +
                       "FROM kavez k " +
                       "JOIN kavez_pas kp " +
                       "ON k.IdKaveza = kp.IdKaveza " +
                       "WHERE k.Naziv IS NOT NULL " +
                       "GROUP BY k.Naziv";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(Query);

            Document pdfReport = new Document();
            PdfWriter.getInstance(pdfReport, new FileOutputStream("reports" + File.separator + "Izvjestaj_kavezi_" + new SimpleDateFormat("dd-MM-yyyy_HH-mm").format(new java.util.Date()) + ".pdf"));
            pdfReport.open();

            Paragraph paragraphAzil = new Paragraph("AzilZaPse - Banja Luka d.o.o");
            paragraphAzil.setAlignment(Element.ALIGN_RIGHT);
            pdfReport.add(paragraphAzil);

            Paragraph paragraphTime = new Paragraph("Vrijeme: " + new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new java.util.Date()));
            paragraphTime.setAlignment(Element.ALIGN_RIGHT);
            pdfReport.add(paragraphTime);

            Image image = Image.getInstance("file:" + "resources" + File.separator + "reportImages" + File.separator + "dog-icon.png");
            image.setBackgroundColor(BaseColor.RED);
            image.setAlignment(Element.ALIGN_CENTER);
            pdfReport.add(image);

            pdfReport.add(new Paragraph("\n\n"));
            pdfReport.add(new Paragraph("Lista svih kaveza u azilu:"));
            pdfReport.add(new Paragraph("\n"));

            PdfPTable pdfPTable = new PdfPTable(4);
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

                    pdfTableCell = new PdfPCell(new Phrase("Kapacitet"));
                    pdfTableCell.setMinimumHeight(25);
                    pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
                    pdfPTable.addCell(pdfTableCell);

                    pdfTableCell = new PdfPCell(new Phrase("Popunjenost kaveza"));
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

                pdfTableCell = new PdfPCell(new Phrase(rs.getString("Naziv")));
                pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setMinimumHeight(20);
                pdfPTable.addCell(pdfTableCell);

                pdfTableCell = new PdfPCell(new Phrase(rs.getString("Kapacitet")));
                pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setMinimumHeight(20);
                pdfPTable.addCell(pdfTableCell);

                pdfTableCell = new PdfPCell(new Phrase(rs.getString("Popunjenost")));
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
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLPdfExporter - exportCages", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    @Override
    public void exportFosters(TableView<FosterParentDTO> fosterParentsTableView, String datumOd) {
        try {
            Document pdfReport = new Document();
            PdfWriter.getInstance(pdfReport, new FileOutputStream("reports" + File.separator + "Izvjestaj_udomitelji_" + new SimpleDateFormat("dd-MM-yyyy_HH-mm").format(new java.util.Date()) + ".pdf"));
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
            pdfReport.add(new Paragraph("Lista svih udomitelja u azilu od datuma " + datumOd + ":"));
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
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLPdfExporter - exportFosters", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    @Override
    public void exportAdoptedDogs(TableView<DogDTO> dogsTableView, String datumOd) {
        try {
            Document pdfReport = new Document();
            PdfWriter.getInstance(pdfReport, new FileOutputStream("reports" + File.separator + "Izvjestaj_udomljeniPsi_" + new SimpleDateFormat("dd-MM-yyyy_HH-mm").format(new java.util.Date()) + ".pdf"));
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
            pdfReport.add(new Paragraph("Lista svih udomljenih pasa u azilu, od datuma " + datumOd + ":"));
            pdfReport.add(new Paragraph("\n"));

            PdfPTable pdfPTable = new PdfPTable(7);
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
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLPdfExporter - exportAdoptedDogs", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    @Override
    public void exportFosterDogJoin() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String Query = "SELECT CONCAT(u.Ime, ' ', u.Prezime) ImeUdomitelja, u.Adresa Adresa, u.BrojTelefona Telefon, p.Ime ImePsa, p.Rasa Rasa, up.DatumOd DatumUdomljavanja " +
                       "FROM udomitelj u " +
                       "JOIN udomljavanjepsa up " +
                       "ON u.JMBG = up.UdomiteljJMBG " +
                       "JOIN pas p " +
                       "ON up.IdPsa = p.IdPsa";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(Query);

            Document pdfReport = new Document();
            PdfWriter.getInstance(pdfReport, new FileOutputStream("reports" + File.separator + "Izvjestaj_UdomljavanjePsa_" + new SimpleDateFormat("dd-MM-yyyy_HH-mm").format(new java.util.Date()) + ".pdf"));
            pdfReport.open();

            Paragraph paragraphAzil = new Paragraph("AzilZaPse - Banja Luka d.o.o");
            paragraphAzil.setAlignment(Element.ALIGN_RIGHT);
            pdfReport.add(paragraphAzil);

            Paragraph paragraphTime = new Paragraph("Vrijeme: " + new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new java.util.Date()));
            paragraphTime.setAlignment(Element.ALIGN_RIGHT);
            pdfReport.add(paragraphTime);

            Image image = Image.getInstance("file:" + "resources" + File.separator + "reportImages" + File.separator + "dog-icon.png");
            image.setBackgroundColor(BaseColor.RED);
            image.setAlignment(Element.ALIGN_CENTER);
            pdfReport.add(image);

            pdfReport.add(new Paragraph("\n\n"));
            pdfReport.add(new Paragraph("Lista svih udomitelja i pasa koje su udomili iz azila:"));
            pdfReport.add(new Paragraph("\n"));

            PdfPTable pdfPTable = new PdfPTable(7);
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

                    pdfTableCell = new PdfPCell(new Phrase("Ime udomitelja"));
                    pdfTableCell.setMinimumHeight(25);
                    pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
                    pdfPTable.addCell(pdfTableCell);

                    pdfTableCell = new PdfPCell(new Phrase("Adresa"));
                    pdfTableCell.setMinimumHeight(25);
                    pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
                    pdfPTable.addCell(pdfTableCell);

                    pdfTableCell = new PdfPCell(new Phrase("Broj telefona"));
                    pdfTableCell.setMinimumHeight(25);
                    pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
                    pdfPTable.addCell(pdfTableCell);

                    pdfTableCell = new PdfPCell(new Phrase("Naziv psa"));
                    pdfTableCell.setMinimumHeight(25);
                    pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
                    pdfPTable.addCell(pdfTableCell);

                    pdfTableCell = new PdfPCell(new Phrase("Rasa"));
                    pdfTableCell.setMinimumHeight(25);
                    pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
                    pdfPTable.addCell(pdfTableCell);

                    pdfTableCell = new PdfPCell(new Phrase("Datum udomljavanja"));
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

                pdfTableCell = new PdfPCell(new Phrase(rs.getString("ImeUdomitelja")));
                pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setMinimumHeight(20);
                pdfPTable.addCell(pdfTableCell);

                pdfTableCell = new PdfPCell(new Phrase(rs.getString("Adresa")));
                pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setMinimumHeight(20);
                pdfPTable.addCell(pdfTableCell);

                pdfTableCell = new PdfPCell(new Phrase(rs.getString("Telefon")));
                pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setMinimumHeight(20);
                pdfPTable.addCell(pdfTableCell);

                pdfTableCell = new PdfPCell(new Phrase(rs.getString("ImePsa")));
                pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setMinimumHeight(20);
                pdfPTable.addCell(pdfTableCell);

                pdfTableCell = new PdfPCell(new Phrase(rs.getString("Rasa")));
                pdfTableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setVerticalAlignment(Element.ALIGN_CENTER);
                pdfTableCell.setMinimumHeight(20);
                pdfPTable.addCell(pdfTableCell);

                pdfTableCell = new PdfPCell(new Phrase(rs.getString("DatumUdomljavanja")));
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
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLPdfExporter - exportFosterDogJoin", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    @Override
    public void exportDogInCage() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String Query = "SELECT k.Naziv NazivKaveza, p.Ime NazivPsa, p.Rasa, p.Visina, p.Tezina, p.Pol, p.DatumRodjenja " +
                       "FROM kavez k " +
                       "JOIN kavez_pas kp " +
                       "ON k.IdKaveza = kp.IdKaveza " +
                       "JOIN pas p " +
                       "ON kp.IdPsa = p.IdPsa " +
                       "WHERE kp.Do IS NULL";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(Query);

            Document pdfReport = new Document();
            PdfWriter.getInstance(pdfReport, new FileOutputStream("reports" + File.separator + "Izvjestaj_PsiUKavezima_" + new SimpleDateFormat("dd-MM-yyyy_HH-mm").format(new java.util.Date()) + ".pdf"));
            pdfReport.open();

            Paragraph paragraphAzil = new Paragraph("AzilZaPse - Banja Luka d.o.o");
            paragraphAzil.setAlignment(Element.ALIGN_RIGHT);
            pdfReport.add(paragraphAzil);

            Paragraph paragraphTime = new Paragraph("Vrijeme: " + new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new java.util.Date()));
            paragraphTime.setAlignment(Element.ALIGN_RIGHT);
            pdfReport.add(paragraphTime);

            Image image = Image.getInstance("file:" + "resources" + File.separator + "reportImages" + File.separator + "dog-icon.png");
            image.setBackgroundColor(BaseColor.RED);
            image.setAlignment(Element.ALIGN_CENTER);
            pdfReport.add(image);

            pdfReport.add(new Paragraph("\n\n"));
            Paragraph title = new Paragraph("Spisak svih kaveza i pasa koji se nalaze u njima:", FontFactory.getFont(FontFactory.TIMES_ROMAN,18, Font.BOLD, BaseColor.BLACK));
            title.setAlignment(Element.ALIGN_CENTER);
            pdfReport.add(title);
            pdfReport.add(new Paragraph("\n"));

            String NazivKaveza_Last = "";
            Paragraph paragraph;
            int i = 0;
            int j = 0;
            while (rs.next()) {
                if (rs.getString("NazivKaveza").equals(NazivKaveza_Last)) {
                    paragraph = new Paragraph("\t\t" + (++j) + ". Pas - " +
                            rs.getString("NazivPsa") + System.lineSeparator() +
                            "\t\t         " + "Rasa: " + rs.getString("Rasa") + System.lineSeparator() +
                            "\t\t         " + "Visina: " + rs.getString("Visina") + " cm" + System.lineSeparator() +
                            "\t\t         " + "Težina: " + rs.getString("Tezina") + " kg" + System.lineSeparator() +
                            "\t\t         " + "Pol: " + (rs.getString("Pol").equals("M") ? "Muški" : "Ženski") + System.lineSeparator() +
                            "\t\t         " + "Datum Rođenja: " + rs.getString("DatumRodjenja"));
                    paragraph.setIndentationLeft(50);
                    pdfReport.add(paragraph);
                }
                else {
                    NazivKaveza_Last = rs.getString("NazivKaveza");
                    j = 0;
                    paragraph = new Paragraph((++i) + ". Kavez - " + rs.getString("NazivKaveza"), FontFactory.getFont(FontFactory.TIMES_ROMAN,16, Font.BOLD, BaseColor.BLACK));
                    pdfReport.add(paragraph);
                    paragraph = new Paragraph("\t\t" + (++j) + ". Pas - " +
                            rs.getString("NazivPsa") + System.lineSeparator() +
                            "\t\t         " + "Rasa: " + rs.getString("Rasa") + System.lineSeparator() +
                            "\t\t         " + "Visina: " + rs.getString("Visina") + " cm" + System.lineSeparator() +
                            "\t\t         " + "Težina: " + rs.getString("Tezina") + " kg" + System.lineSeparator() +
                            "\t\t         " + "Pol: " + (rs.getString("Pol").equals("M") ? "Muški" : "Ženski") + System.lineSeparator() +
                            "\t\t         " + "Datum Rođenja: " + rs.getString("DatumRodjenja"));
                    paragraph.setIndentationLeft(50);
                    pdfReport.add(paragraph);
                }
            }

            pdfReport.close();
            rs.close();
            statement.close();
            conn.close();
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLPdfExporter - exportDogInCage", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }

    }

}
