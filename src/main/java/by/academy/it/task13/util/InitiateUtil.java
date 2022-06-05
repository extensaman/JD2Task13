package by.academy.it.task13.util;

import by.academy.it.task13.entity.Certificate;
import by.academy.it.task13.entity.CertificateType;
import by.academy.it.task13.entity.CertificateDecoration;
import by.academy.it.task13.service.CertificateDecorationService;
import by.academy.it.task13.service.CertificateService;
import by.academy.it.task13.service.CertificateTypeService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InitiateUtil implements CommandLineRunner {

    private static final Logger LOGGER = LogManager.getLogger(InitiateUtil.class);

    @Autowired
    private final CertificateTypeService certificateTypeService;
    @Autowired
    private final CertificateService certificateService;
    @Autowired
    private final CertificateDecorationService certificateDecorationService;

    @Override
    public void run(String[] args) throws Exception {
        CertificateType certificateType01 = CertificateType.builder()
                .activity(true)
                .name("Для одного")
                .build();
        CertificateType certificateType02 = CertificateType.builder()
                .activity(false)
                .name("Для пары")
                .build();
        CertificateType certificateType03 = CertificateType.builder()
                .activity(true)
                .name("Для семьи")
                .build();
        CertificateType certificateType04 = CertificateType.builder()
                .activity(true)
                .name("Для детей")
                .build();
        List<CertificateType> certificateTypeList = new ArrayList<>(
                Arrays.asList(
                        certificateType01,
                        certificateType02,
                        certificateType03,
                        certificateType04
                )
        );
        certificateTypeService.saveAll(certificateTypeList);
        LOGGER.info("Initialization of 'CertificateType' done");

        Certificate certificate01 = Certificate.builder()
                .activity(true)
                .certificateType(certificateType01)
                .name("Всадник")
                .description("Предполагает индивидуальное занятие по обучению верховой езде. Под руководством опытного тренера ваши друзья научатся управлять лошадью, ездить разными аллюрами и почувствуют себя настоящими Всадниками! Нетривиально провести праздник, отключиться от всех забот и зарядиться позитивом — комплексное решение для отличного подарка!")
                .horseCount(1)
                .duration(1.0)
                .price(BigDecimal.valueOf(52.5))
                .photographerIncluded(false)
                .build();
        Certificate certificate02 = Certificate.builder()
                .activity(true)
                .certificateType(certificateType01)
                .name("Всадник+")
                .description("Катание на лошади (индивидуальное занятие верховой ездой) + часовая аренда лошади для самостоятельной фотосессии (в сопровождении ассистента)")
                .horseCount(1)
                .duration(2.0)
                .price(BigDecimal.valueOf(80.0))
                .photographerIncluded(true)
                .build();
        Certificate certificate03 = Certificate.builder()
                .activity(true)
                .certificateType(certificateType01)
                .name("Лесной")
                .description("Обучение (инструктаж) 30 минут на плацу или в манеж + 60 минут прогулка в лес. Программа подходит для начинающих всадников")
                .horseCount(1)
                .duration(1.5)
                .price(BigDecimal.valueOf(75.0))
                .photographerIncluded(false)
                .build();
        Certificate certificate10 = Certificate.builder()
                .activity(true)
                .certificateType(certificateType01)
                .name("Властелин коня")
                .description("Курс занятий верховой ездой (абонемент на 10 часов), в течение которых всадник осваивает азы управления лошадью и езду всеми аллюрами: шагом, рысью и галопом. Занятия проходят в мини группе.")
                .horseCount(1)
                .duration(10.0)
                .price(BigDecimal.valueOf(340.0))
                .photographerIncluded(false)
                .build();
        certificateService.saveAll(
                List.of(certificate01,certificate02,certificate03, certificate10));
        LOGGER.info("Initialization of 'Certificate' done");

        CertificateDecoration certificateDecoration01 = CertificateDecoration.builder()
                .activity(true)
                .name("Электронный сертификат")
                .description("Любой из представленных сертификатов может быть оформлен в электронном виде. Дизайн сертификата разрабатывался специально для печати. А если распечатать его цветным на плотной бумаге, то он ни чем не уступает сертификатам, напечатанным в типографии. Этот сертификат можно послать по почте в элеронном виде. Сразу после оплаты готовый к печати файл придёт Вам на почту. Просто и быстро.")
                .price(BigDecimal.ZERO)
                .build();
        CertificateDecoration certificateDecoration02 = CertificateDecoration.builder()
                .activity(true)
                .name("Сертификат в конверте")
                .description("Подарочный сертификат в конверте выполнен в разных стилях: летний (зелёный), зимний (голубой) и универсальны (коричневый). Сертификат-вкладыш напечатана на плотной бумаге и облечён в красивую супер-обложку. На вкладыше внутри указывается название подарка и имя того, кому он торжественно вручается.")
                .price(BigDecimal.TEN)
                .build();
        CertificateDecoration certificateDecoration03 = CertificateDecoration.builder()
                .activity(true)
                .name("Пластиковая карта")
                .description("Подарочный сертификат на любую сумму от 200р избавляет Вас от необходимости выбора подарка за тех, кому он вручается. Вы определяете сумму подарка, остальное выбирает счастливый обладатель подарка! Фотосессии и катание на лошадях, курс занятий верховой ездой, катание на пони и даже конные походы – каждый найдёт себе подарок по душе!")
                .price(BigDecimal.valueOf(15.0))
                .build();
        CertificateDecoration certificateDecoration04 = CertificateDecoration.builder()
                .activity(true)
                .name("Подарочная корзинка")
                .description("Подарочный сертификат в упаковке включает в себя: коробочку из бересты с сеном, резную лошадку из художественной школы в Сморгони, подарочный сертификат в конверте и печенье из злаков для лошадей (и их всадников). Корзинка упаковывается в плёнку или доставляется без упаковки, если Вы захотите добавить в неё записку или письмо от Деда мороза.")
                .price(BigDecimal.valueOf(30.0))
                .build();
        certificateDecorationService.saveAll(
                List.of(certificateDecoration01,
                        certificateDecoration02,
                        certificateDecoration03,
                        certificateDecoration04));
        LOGGER.info("Initialization of 'CertificateDecoration' done");
    }
}
