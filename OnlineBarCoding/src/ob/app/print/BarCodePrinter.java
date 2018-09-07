package ob.app.print;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.event.PrintJobAdapter;
import javax.print.event.PrintJobEvent;

public class BarCodePrinter {
	public static void printScaleWgt(String barcode) throws PrintException, IOException {
		// instane from TireDetailsBean Class

		// w -->offset from the left in printout
		int w = 0;

		// ^CF0,60 ^FO220,50^FDInternational Shipping, Inc.^FS ^FO50,250^GB700,1,3^FS

		String PrintCommand = "^XA" + "" +

				"^FO" + (w + 50) + ",1" + "  ^BY2" + "^BCN,120,N,N,S" + "^FD" + barcode
				+ "^FS  ^CF0,40  ^FO" + (w + 100) + ",130^FD"+barcode+"^FS " + "  ^XZ";
	
		String defaultPrinter = PrintServiceLookup.lookupDefaultPrintService().getName();
		// System.out.println("Default printer: " + defaultPrinter);
		PrintService service = PrintServiceLookup.lookupDefaultPrintService();

		// prints the famous hello world! plus a form feed
		InputStream is = new ByteArrayInputStream(PrintCommand.getBytes("UTF8"));

		PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
		pras.add(new Copies(1));

		DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
		Doc doc = new SimpleDoc(is, flavor, null);
		DocPrintJob job = service.createPrintJob();

		PrintJobWatcher pjw = new PrintJobWatcher(job);
		job.print(doc, pras);
		pjw.waitForDone();
		is.close();
	}
}

class PrintJobWatcher {
	boolean done = false;

	PrintJobWatcher(DocPrintJob job) {
		job.addPrintJobListener(new PrintJobAdapter() {
			public void printJobCanceled(PrintJobEvent pje) {
				allDone();
			}

			public void printJobCompleted(PrintJobEvent pje) {
				allDone();
			}

			public void printJobFailed(PrintJobEvent pje) {
				allDone();
			}

			public void printJobNoMoreEvents(PrintJobEvent pje) {
				allDone();
			}

			void allDone() {
				synchronized (PrintJobWatcher.this) {
					done = true;
					System.out.println("Printing done ...");
					PrintJobWatcher.this.notify();
				}
			}
		});
	}

	public synchronized void waitForDone() {
		try {
			while (!done) {
				wait();
			}
		} catch (InterruptedException e) {
		}
	}
}
