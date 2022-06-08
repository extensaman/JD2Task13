package by.academy.it.task13.controller;

import by.academy.it.task13.controller.admin.AdminCertificateController;
import by.academy.it.task13.entity.Certificate;
import by.academy.it.task13.entity.CertificateType;
import by.academy.it.task13.service.CertificateService;
import by.academy.it.task13.service.CertificateTypeService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(Constant.CERTIFICATE_MAPPING)
@RequiredArgsConstructor
public class CertificateController {
    private static final Logger LOGGER = LogManager.getLogger(AdminCertificateController.class);

    @Autowired
    private final CertificateService certificateService;

    @Autowired
    private final CertificateTypeService certificateTypeService;

    @GetMapping
    public String getCertificatePage(Model model) {
        List<Certificate> activeCertificateList = certificateService.findAll().stream()
                .filter(Certificate::isActivity)
                .collect(Collectors.toList());
        List<CertificateType> activeCertificateTypeList = getActiveCertificateTypeList();
        model.addAttribute(Constant.ACTIVE_CERTIFICATE_LIST, activeCertificateList);
        model.addAttribute(Constant.ACTIVE_CERTIFICATE_TYPE_LIST, activeCertificateTypeList);
        model.addAttribute(Constant.TITLE,
                Constant.TITLE_CERTIFICATE_MESSAGE);
        return Constant.CERTIFICATE_PAGE;
    }

    @GetMapping("/{id}")
    public String getCertificateWithSpecificType(@PathVariable String id, Model model) {
        LOGGER.info("Getting certificates with type's id = " + id);

        List<Certificate> certificateListWithSpecificType = certificateTypeService.findById(id)
                .map(type ->
                        certificateService.findAll().stream()
                                .filter(certificate ->
                                        certificate.getCertificateType().equals(type) &&
                                                certificate.isActivity())
                                .collect(Collectors.toList())
                ).orElse(null);
        List<CertificateType> activeCertificateTypeList = getActiveCertificateTypeList();
        model.addAttribute(Constant.ACTIVE_CERTIFICATE_LIST, certificateListWithSpecificType);
        model.addAttribute(Constant.ACTIVE_CERTIFICATE_TYPE_LIST, activeCertificateTypeList);
        model.addAttribute(Constant.TITLE,
                Constant.TITLE_CERTIFICATE_MESSAGE);
        return Constant.CERTIFICATE_PAGE;
    }

    private List<CertificateType> getActiveCertificateTypeList() {
        return certificateTypeService.findAll().stream()
                .filter(CertificateType::isActivity)
                .collect(Collectors.toList());
    }

}
