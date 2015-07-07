package tests;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import rs.ac.uns.ftn.xws.util.GenerateReport;

public class JasperTest{

    @Test
    public void generateLagerLista() {
        File f = new File("pdfs/LagerLista.pdf");
        f.delete();
        GenerateReport.createLagerList();  

        f = new File("pdfs/LagerLista.pdf");
        Assert.assertTrue(f.exists());
    }

    @Test
    public void generateAnalitika() {
        File f = new File("pdfs/Analitika.pdf");
        f.delete();
        GenerateReport.createAnalitika(2);
        f = new File("pdfs/Analitika.pdf");
        Assert.assertTrue(f.exists());
    }

} 

