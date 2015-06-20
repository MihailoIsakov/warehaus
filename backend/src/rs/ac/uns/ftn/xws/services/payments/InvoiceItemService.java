package rs.ac.uns.ftn.xws.services.payments;

import javax.ejb.EJB;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.xws.entities.payments.Invoice;
import rs.ac.uns.ftn.xws.sessionbeans.payments.InvoiceItemDaoLocal;
import rs.ac.uns.ftn.xws.util.Authenticate;

@Path("/invoiceItem")
public class InvoiceItemService {

	private static Logger log = Logger.getLogger(Invoice.class);

	@EJB
	private InvoiceItemDaoLocal invoiceItemDao;



}
