package by.academy.it.task13.controller;

import by.academy.it.task13.entity.Certificate;
import by.academy.it.task13.entity.CertificateDecoration;
import by.academy.it.task13.entity.Ordering;
import by.academy.it.task13.service.CertificateDecorationService;
import by.academy.it.task13.service.CertificateService;
import by.academy.it.task13.service.CertificateTypeService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(Constant.CERTIFICATE_MAPPING)
@RequiredArgsConstructor
public class CertificateController {
    private static final Logger LOGGER = LogManager.getLogger(CertificateController.class);

    private final CertificateService certificateService;
    private final CertificateTypeService certificateTypeService;
    private final CertificateDecorationService certificateDecorationService;

    @GetMapping
    public String getCertificatePage(Model model) {
        LOGGER.info("getCertificatePage");
        // TODO Need make specific method in Repository
        /*List<Certificate> activeCertificateList = certificateService.findAll().stream()
                .filter(certificate -> certificate.isActivity() && certificate.getCertificateType().isActivity())
                .collect(Collectors.toList());
        List<CertificateType> activeCertificateTypeList = getActiveCertificateTypeList();*/
        model.addAttribute(Constant.ACTIVE_CERTIFICATE_LIST, certificateService.findAllActiveCertificate());
        model.addAttribute(Constant.ACTIVE_CERTIFICATE_TYPE_LIST, certificateTypeService.findAllActiveCertificateType());
        model.addAttribute(Constant.TITLE,
                Constant.TITLE_CERTIFICATE_MESSAGE);
        return Constant.CERTIFICATE_PAGE;
    }

    @GetMapping("/{id}")
    public String getCertificateWithSpecificType(@PathVariable String id, Model model) {
        LOGGER.info("Getting certificates with type's id = " + id);

/*        List<Certificate> certificateListWithSpecificType = certificateTypeService.findById(id)
                .map(type ->
                        certificateService.findAll().stream()
                                .filter(certificate ->
                                        certificate.getCertificateType().equals(type) &&
                                                certificate.isActivity())
                                .collect(Collectors.toList())
                ).orElse(null);
        List<CertificateType> activeCertificateTypeList = getActiveCertificateTypeList();*/
        model.addAttribute(Constant.ACTIVE_CERTIFICATE_LIST,
                certificateService.findCertificatesByActivityTrueAndCertificateTypeId(id));
        model.addAttribute(Constant.ACTIVE_CERTIFICATE_TYPE_LIST,
                certificateTypeService.findAllActiveCertificateType());
        model.addAttribute(Constant.TITLE,
                Constant.TITLE_CERTIFICATE_MESSAGE);
        return Constant.CERTIFICATE_PAGE;
    }

    @PostMapping(Constant.ORDER_MAPPING)
    public String orderCertificate(@ModelAttribute Certificate certificate, Model model) {
        List<CertificateDecoration> activeCertificateDecorationList = certificateDecorationService.findAll().stream()
                .filter(CertificateDecoration::isActivity)
                .collect(Collectors.toList());
        //model.addAttribute(Constant.CERTIFICATE, certificate);
        model.addAttribute(Constant.ACTIVE_CERTIFICATE_DECORATION_LIST, activeCertificateDecorationList);
        model.addAttribute(Constant.CERTIFICATE_ORDER, new Ordering());
        model.addAttribute(Constant.TITLE,
                Constant.TITLE_CERTIFICATE_ORDER_MESSAGE);
        return Constant.CERTIFICATE_ORDER_PAGE;
    }

/*    private List<CertificateType> getActiveCertificateTypeList() {
        return certificateTypeService.findAll().stream()
                .filter(CertificateType::isActivity)
                .collect(Collectors.toList());
    }*/

}
