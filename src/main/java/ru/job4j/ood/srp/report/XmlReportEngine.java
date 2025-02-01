package ru.job4j.ood.srp.report;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.model.EmployeeForXmlRoot;
import ru.job4j.ood.srp.model.EmployeeWithStrings;
import ru.job4j.ood.srp.store.Store;
import java.io.StringWriter;
import java.util.List;
import java.util.function.Predicate;

public class XmlReportEngine implements Report {
    private final Store store;
    private final JAXBContext context;

    public XmlReportEngine(Store store) {
        this.store = store;
        try {
            this.context = JAXBContext.newInstance(EmployeeForXmlRoot.class);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<EmployeeWithStrings> employees = store.findBy(filter).stream()
                .map(EmployeeWithStrings::new)
                .toList();
        try {
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            EmployeeForXmlRoot employeeFinal = new EmployeeForXmlRoot(employees);
            StringWriter writer = new StringWriter();
            marshaller.marshal(employeeFinal, writer);
            return writer.getBuffer().toString();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}