package net.opsource.simpleapp.util;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class JaxbUtil {

	public static <T> void marshall(final T object, final File xmlFile) {
		try {
			final JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
			final Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(object, xmlFile);
			jaxbMarshaller.marshal(object, System.out);
		} catch (JAXBException e) {
			throw new JaxbException("Exception while marshalling [" + object.getClass().getName() + "]", e);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T unmarshall(final Class<T> clazz, final File xmlFile) {
		try {
			final JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
			final Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			return (T) jaxbUnmarshaller.unmarshal(xmlFile);
		} catch (JAXBException e) {
			throw new JaxbException("Exception while unmarshalling [" + clazz.getClass().getName() + "]", e);
		}
	}

}
