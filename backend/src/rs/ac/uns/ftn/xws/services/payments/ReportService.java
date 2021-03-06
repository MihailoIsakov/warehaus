package rs.ac.uns.ftn.xws.services.payments;

import java.io.File;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import model.Sektor;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.xws.util.Authenticate;
import rs.ac.uns.ftn.xws.util.GenerateReport;

import daoBean.MagacinskaKarticaDaoLocal;
import daoBean.PrometniDokumentDaoLocal;
import daoBean.SektorDaoLocal;

@Path("/report")
public class ReportService {
	private static Logger log = Logger.getLogger(ReportService.class);

    private static final String lagerListaURI = "pdfs/LagerLista.pdf";
    private static final String analitikaURI  = "pdfs/Analitika.pdf";
    private static final String popisURI      = "pdfs/PopisniDokument.pdf";
    
	
	@GET 
    @Path("lager")
    @Produces("application/pdf")
    public Response generateLagerLista() {
        GenerateReport.createLagerList();
        File file = new File(lagerListaURI);

        ResponseBuilder response = Response.ok((Object) file);
        response.header("Content-Disposition",
                "inline; filename=LagerLista.pdf");

        return response.build();
    }

    @GET
    @Path("analitika/{id}")
    @Produces("application/pdf")
    public Response generateAnalitika(@PathParam("id") String id) {
        GenerateReport.createAnalitika(Integer.parseInt(id));
        File file = new File(analitikaURI);

        ResponseBuilder response = Response.ok((Object) file);
        response.header("Content-Disposition",
                "inline; filename=Analitika.pdf;");

        return response.build();
    }

    @GET
    @Path("popis/{id}")
    @Produces("application/pdf")
    public Response generatePopisniDokument(@PathParam("id") String id) {
        GenerateReport.createPopis(Integer.parseInt(id));
        File file = new File(popisURI);
    
        ResponseBuilder response = Response.ok((Object) file);
        response.header("Content-Disposition",
                "inline; filename=PopisniDokument.pdf;");

        return response.build();
    }
}
