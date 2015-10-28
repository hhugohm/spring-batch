package org.neos.batch.adapter;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class LocalDateAdapter extends XmlAdapter<String, DateTime >{

	@Override
	public DateTime  unmarshal(String v) throws Exception {
        return new DateTime (v);
    }

	@Override
	 public String marshal(DateTime  v) throws Exception {
		//se realiza un format de la fecha
		DateTimeFormatter dtfOut = DateTimeFormat.forPattern("dd/MM/yyyy");
        return dtfOut.print(v);
    }

}
