package by.academy.it.task13.util;

import by.academy.it.task13.AppSetting;
import by.academy.it.task13.configuration.MvcConfiguration;
import by.academy.it.task13.entity.Attachment;
import by.academy.it.task13.entity.Certificate;
import by.academy.it.task13.entity.CertificateDecoration;
import by.academy.it.task13.entity.CertificateOrder;
import by.academy.it.task13.entity.CertificateType;
import by.academy.it.task13.entity.Coach;
import by.academy.it.task13.entity.Horse;
import by.academy.it.task13.entity.OrderStatus;
import by.academy.it.task13.entity.PhotoSession;
import by.academy.it.task13.entity.User;
import by.academy.it.task13.service.AttachmentService;
import by.academy.it.task13.service.CertificateDecorationService;
import by.academy.it.task13.service.CertificateOrderService;
import by.academy.it.task13.service.CertificateService;
import by.academy.it.task13.service.CertificateTypeService;
import by.academy.it.task13.service.CoachService;
import by.academy.it.task13.service.HorseService;
import by.academy.it.task13.service.PhotoSessionService;
import by.academy.it.task13.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InitiateUtil implements CommandLineRunner {
    private static final Logger LOGGER = LogManager.getLogger(InitiateUtil.class);

    private final ApplicationContext context;

    private final CertificateTypeService certificateTypeService;
    private final CertificateService certificateService;
    private final CertificateDecorationService certificateDecorationService;
    private final CertificateOrderService certificateOrderService;
    private final PhotoSessionService photoSessionService;
    private final HorseService horseService;
    private final CoachService coachService;
    private final UserService userService;
    private final AttachmentService attachmentService;
    private final ImageFileList imageFileList;
    private final PasswordEncoder encoder;
    private final AppSetting appSetting;


    @Override
    public void run(String[] args) throws Exception {

        new File(appSetting.getUploadPath()).mkdirs();

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
                .photoFile("certificate_single_01.jpg")
                .build();
        Certificate certificate02 = Certificate.builder()
                .activity(true)
                .certificateType(certificateType01)
                .name("Всадник+")
                .description("Катание на лошади (индивидуальное занятие верховой ездой) + часовая аренда лошади для самостоятельной фотосессии (в сопровождении ассистента)")
                .horseCount(1)
                .duration(2.0)
                .price(BigDecimal.valueOf(80.0))
                .photographerIncluded(false)
                .photoFile("certificate_single_02.jpg")
                .build();
        Certificate certificate03 = Certificate.builder()
                .activity(true)
                .certificateType(certificateType01)
                .name("Лесной")
                .description("Обучение (инструктаж) 30 минут на плацу или в манеже + 60 минут прогулка в лес. Программа подходит для начинающих всадников")
                .horseCount(1)
                .duration(1.5)
                .price(BigDecimal.valueOf(75.0))
                .photographerIncluded(false)
                .photoFile("certificate_single_03.jpg")
                .build();
        Certificate certificate04 = Certificate.builder()
                .activity(true)
                .certificateType(certificateType01)
                .name("Лесная прогулка")
                .description("Обучение (инструктаж) 60 минут на плацу или в манеж + 60 минут прогулка в лес. Программа подходит для начинающих всадников")
                .horseCount(1)
                .duration(2.0)
                .price(BigDecimal.valueOf(85.0))
                .photographerIncluded(false)
                .photoFile("certificate_single_04.jpg")
                .build();
        Certificate certificate05 = Certificate.builder()
                .activity(true)
                .certificateType(certificateType01)
                .name("Фотосессия мини")
                .description("Подарочный сертификат на Мини-фотосъёмку с лошадьми, продолжительностью 30 минут")
                .horseCount(1)
                .duration(0.5)
                .price(BigDecimal.valueOf(100.0))
                .photographerIncluded(true)
                .photoFile("certificate_single_05.jpg")
                .build();
        Certificate certificate06 = Certificate.builder()
                .activity(true)
                .certificateType(certificateType01)
                .name("Амазонка")
                .description("Фотосессия с лошадью с фотографом клуба (аренда лошади, работа фотографа и ассистента, 20 фотографий в обработке: 10 штук – цветокоррекция, 10 штук – ретушь)")
                .horseCount(1)
                .duration(1.0)
                .price(BigDecimal.valueOf(200.0))
                .photographerIncluded(true)
                .photoFile("certificate_single_06.jpg")
                .build();
        Certificate certificate07 = Certificate.builder()
                .activity(true)
                .certificateType(certificateType01)
                .name("Сказка")
                .description("Катание на лошади (индивидуальное занятие верховой ездой) и фотосессия с лошадью с фотографом клуба (аренда лошади, работа фотографа и ассистента)")
                .horseCount(1)
                .duration(2.0)
                .price(BigDecimal.valueOf(240.0))
                .photographerIncluded(true)
                .photoFile("certificate_single_07.jpg")
                .build();
        Certificate certificate08 = Certificate.builder()
                .activity(true)
                .certificateType(certificateType01)
                .name("Драйв")
                .description("Курс занятий верховой ездой из 6-ти занятий. Занятия проходят в мини группе.")
                .horseCount(1)
                .duration(6.0)
                .price(BigDecimal.valueOf(210.0))
                .photographerIncluded(false)
                .photoFile("certificate_single_08.jpg")
                .build();
        Certificate certificate09 = Certificate.builder()
                .activity(true)
                .certificateType(certificateType01)
                .name("Погружение")
                .description("Курс индивидуальных занятий верховой ездой (5 часов)")
                .horseCount(1)
                .duration(5.0)
                .price(BigDecimal.valueOf(230.0))
                .photographerIncluded(false)
                .photoFile("certificate_single_09.jpg")
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
                .photoFile("certificate_single_10.jpg")
                .build();
        Certificate certificate11 = Certificate.builder()
                .activity(true)
                .certificateType(certificateType02)
                .name("Романтика")
                .description("Катание на лошади для пары (индивидуальное занятие верховой ездой для двоих)")
                .horseCount(2)
                .duration(1.0)
                .price(BigDecimal.valueOf(100.0))
                .photographerIncluded(false)
                .photoFile("certificate_family_01.jpg")
                .build();
        Certificate certificate12 = Certificate.builder()
                .activity(true)
                .certificateType(certificateType02)
                .name("Романтика +")
                .description("Катание на лошади для пары (индивидуальное занятие верховой ездой) + часовая аренда лошади для самостоятельной фотосессии (в сопровождении ассистента)")
                .horseCount(2)
                .duration(2.0)
                .price(BigDecimal.valueOf(130.0))
                .photographerIncluded(false)
                .photoFile("certificate_family_02.jpg")
                .build();
        Certificate certificate13 = Certificate.builder()
                .activity(true)
                .certificateType(certificateType02)
                .name("Романтика Леса")
                .description("Индивидуальная программа для двоих, включающая получасовой инструктаж (30 минут обучение) и часовую прогулку по лесу (60 минут прогулка) с фотографиями в лесу на телефон/фотоаппарат участников")
                .horseCount(2)
                .duration(1.5)
                .price(BigDecimal.valueOf(140.0))
                .photographerIncluded(false)
                .photoFile("certificate_family_03.jpg")
                .build();
        Certificate certificate14 = Certificate.builder()
                .activity(true)
                .certificateType(certificateType02)
                .name("Созвездие — 2")
                .description("Верховая езда для пары, друзей, семьи (два часа — для двоих)")
                .horseCount(2)
                .duration(1.0)
                .price(BigDecimal.valueOf(92.0))
                .photographerIncluded(false)
                .photoFile("certificate_family_04.jpg")
                .build();
        Certificate certificate15 = Certificate.builder()
                .activity(true)
                .certificateType(certificateType02)
                .name("Кантри — 1")
                .description("Фотосессия с лошадью для пары")
                .horseCount(1)
                .duration(1.0)
                .price(BigDecimal.valueOf(220.0))
                .photographerIncluded(true)
                .photoFile("certificate_family_05.jpg")
                .build();
        Certificate certificate16 = Certificate.builder()
                .activity(true)
                .certificateType(certificateType02)
                .name("Кантри — 2")
                .description("Фотосессия с двумя лошадьми для пары")
                .horseCount(2)
                .duration(1.0)
                .price(BigDecimal.valueOf(260.0))
                .photographerIncluded(true)
                .photoFile("certificate_family_06.jpg")
                .build();
        Certificate certificate17 = Certificate.builder()
                .activity(true)
                .certificateType(certificateType02)
                .name("Рандеву – 1")
                .description("Индивидуальное занятие верховой ездой для пары + фотосессия с одной лошадью с фотографом клуба")
                .horseCount(2)
                .duration(2.0)
                .price(BigDecimal.valueOf(300.0))
                .photographerIncluded(true)
                .photoFile("certificate_family_07.jpg")
                .build();
        Certificate certificate18 = Certificate.builder()
                .activity(true)
                .certificateType(certificateType02)
                .name("Рандеву – 2")
                .description("Индивидуальное занятие верховой ездой для пары + фотосессия с двумя лошадьми и фотографом клуба")
                .horseCount(2)
                .duration(2.0)
                .price(BigDecimal.valueOf(320.0))
                .photographerIncluded(true)
                .photoFile("certificate_family_08.jpg")
                .build();
        Certificate certificate19 = Certificate.builder()
                .activity(true)
                .certificateType(certificateType02)
                .name("Богатырский")
                .description("Обучение 60 мин плюс 60 мин прогулка в лес для пары, фотосессия с двумя лошадьми")
                .horseCount(2)
                .duration(3.0)
                .price(BigDecimal.valueOf(450.0))
                .photographerIncluded(true)
                .photoFile("certificate_family_09.png")
                .build();
        Certificate certificate20 = Certificate.builder()
                .activity(true)
                .certificateType(certificateType03)
                .name("Созвездие — 3")
                .description("Верховая езда для компании, семьи (три часа — для троих)")
                .horseCount(3)
                .duration(1.0)
                .price(BigDecimal.valueOf(138.0))
                .photographerIncluded(false)
                .photoFile("certificate_big_family_01.jpg")
                .build();
        Certificate certificate21 = Certificate.builder()
                .activity(true)
                .certificateType(certificateType03)
                .name("Созвездие — 4")
                .description("Верховая езда для компании, семьи (четыре часа — 4 занятия)")
                .horseCount(4)
                .duration(1.0)
                .price(BigDecimal.valueOf(184.0))
                .photographerIncluded(false)
                .photoFile("certificate_big_family_02.jpg")
                .build();
        Certificate certificate22 = Certificate.builder()
                .activity(true)
                .certificateType(certificateType04)
                .name("Радость")
                .description("Катание на лошади или пони для ребёнка (индивидуальное занятие верховой ездой)")
                .horseCount(1)
                .duration(1.0)
                .price(BigDecimal.valueOf(52.0))
                .photographerIncluded(false)
                .photoFile("certificate_child_01.jpg")
                .build();
        Certificate certificate23 = Certificate.builder()
                .activity(true)
                .certificateType(certificateType04)
                .name("Радость +")
                .description("Катание на лошади или пони для ребёнка (индивидуальное занятие верховой ездой) + часовая арнеда лошади для самостоятельной фотосессии (в сопровождении ассистента)")
                .horseCount(1)
                .duration(2.0)
                .price(BigDecimal.valueOf(80.0))
                .photographerIncluded(false)
                .photoFile("certificate_child_02.jpg")
                .build();
        Certificate certificate24 = Certificate.builder()
                .activity(true)
                .certificateType(certificateType04)
                .name("Драйв-пони")
                .description("Курс занятий на пони из 6 занятий по 30мин для ребёнка (индивидуальное занятие верховой ездой)")
                .horseCount(1)
                .duration(3.0)
                .price(BigDecimal.valueOf(108.0))
                .photographerIncluded(false)
                .photoFile("certificate_child_03.jpg")
                .build();
        certificateService.saveAll(
                List.of(certificate01,
                        certificate02,
                        certificate03,
                        certificate04,
                        certificate05,
                        certificate06,
                        certificate07,
                        certificate08,
                        certificate09,
                        certificate10,
                        certificate11,
                        certificate12,
                        certificate13,
                        certificate14,
                        certificate15,
                        certificate16,
                        certificate17,
                        certificate18,
                        certificate19,
                        certificate20,
                        certificate21,
                        certificate22,
                        certificate23,
                        certificate24));
        LOGGER.info("Initialization of 'Certificate' done");

        CertificateDecoration certificateDecoration01 = CertificateDecoration.builder()
                .activity(true)
                .name("Электронный сертификат")
                .description("Любой из представленных сертификатов может быть оформлен в электронном виде. Дизайн сертификата разрабатывался специально для печати. А если распечатать его цветным на плотной бумаге, то он ни чем не уступает сертификатам, напечатанным в типографии. Этот сертификат можно послать по почте в элеронном виде. Сразу после оплаты готовый к печати файл придёт Вам на почту. Просто и быстро.")
                .price(BigDecimal.ZERO)
                .photoFile("certificate_decoration_01.jpg")
                .deliveryNecessity(false)
                .build();
        CertificateDecoration certificateDecoration02 = CertificateDecoration.builder()
                .activity(true)
                .name("Сертификат в конверте")
                .description("Подарочный сертификат в конверте выполнен в разных стилях: летний (зелёный), зимний (голубой) и универсальны (коричневый). Сертификат-вкладыш напечатана на плотной бумаге и облечён в красивую супер-обложку. На вкладыше внутри указывается название подарка и имя того, кому он торжественно вручается.")
                .price(BigDecimal.TEN)
                .deliveryNecessity(true)
                .photoFile("certificate_decoration_02.jpg")
                .build();
        CertificateDecoration certificateDecoration03 = CertificateDecoration.builder()
                .activity(true)
                .name("Пластиковая карта")
                .description("Подарочный сертификат избавляет Вас от необходимости выбора подарка за тех, кому он вручается. Вы определяете сумму подарка, остальное выбирает счастливый обладатель подарка! Фотосессии и катание на лошадях, курс занятий верховой ездой, катание на пони и даже конные походы – каждый найдёт себе подарок по душе!")
                .price(BigDecimal.valueOf(15.0))
                .deliveryNecessity(true)
                .photoFile("certificate_decoration_03.jpg")
                .build();
        CertificateDecoration certificateDecoration04 = CertificateDecoration.builder()
                .activity(true)
                .name("Подарочная корзинка")
                .description("Подарочный сертификат в упаковке включает в себя: коробочку из бересты с сеном, резную лошадку из художественной школы в Сморгони, подарочный сертификат в конверте и печенье из злаков для лошадей (и их всадников). Корзинка упаковывается в плёнку или доставляется без упаковки, если Вы захотите добавить в неё записку или письмо от Деда мороза.")
                .price(BigDecimal.valueOf(30.0))
                .deliveryNecessity(true)
                .photoFile("certificate_decoration_04.jpg")
                .build();
        certificateDecorationService.saveAll(
                List.of(certificateDecoration01,
                        certificateDecoration02,
                        certificateDecoration03,
                        certificateDecoration04));
        LOGGER.info("Initialization of 'CertificateDecoration' done");

        PhotoSession photoSession01 = PhotoSession.builder()
                .activity(true)
                .name("Минифотосессия")
                .description("8-10 фотографий (4-5 фотографий в лёгкой ретуши, 4-5 фотографий в цветокоррекции)")
                .photoFile("photosession_mini.jpg")
                .horseCount(1)
                .duration(30)
                .price(BigDecimal.valueOf(100.0))
                .build();
        PhotoSession photoSession02 = PhotoSession.builder()
                .activity(true)
                .name("Индивидуальная")
                .description("20 фотографий (10 фотографий в лёгкой ретуши, 10 фотографий в цветокоррекции)")
                .photoFile("photosession_single.png")
                .horseCount(1)
                .duration(60)
                .price(BigDecimal.valueOf(200.0))
                .build();
        PhotoSession photoSession03 = PhotoSession.builder()
                .activity(true)
                .name("Для семьи")
                .description("20-25 фотографий (10 фотографий в лёгкой ретуши, 15 фотографий в цветокоррекции)")
                .photoFile("photosession_family_01.png")
                .horseCount(1)
                .duration(60)
                .price(BigDecimal.valueOf(220.0))
                .build();
        PhotoSession photoSession04 = PhotoSession.builder()
                .activity(true)
                .name("Для семьи+")
                .description("20-25 фотографий (10 фотографий в лёгкой ретуши, 15 фотографий в цветокоррекции)")
                .photoFile("photosession_family_02.png")
                .horseCount(2)
                .duration(60)
                .price(BigDecimal.valueOf(260.0))
                .build();
        PhotoSession photoSession05 = PhotoSession.builder()
                .activity(true)
                .name("Самостоятельная")
                .description("Прекрасная возможность провести собственную фотосессию в сопровождении ассистента")
                .photoFile("photosession_yourself.jpg")
                .horseCount(1)
                .duration(60)
                .price(BigDecimal.valueOf(60.0))
                .build();
        photoSessionService.saveAll(
                List.of(photoSession01,
                        photoSession02,
                        photoSession03,
                        photoSession04,
                        photoSession05));
        LOGGER.info("Initialization of 'PhotoSession' done");

        Horse horse01 = Horse.builder()
                .activity(true)
                .name("Грей")
                .description("Белазим (Зимогор — 20647 Биалка), серый мерин, спортивная помесь русской верховой и орловской рысистой породы, Курский конный завод")
                .photoFile("horse_01.jpg")
                .build();
        Horse horse02 = Horse.builder()
                .activity(true)
                .name("Рада")
                .description("Рада (621 Туляк — 1421 Орхидея), вороная кобыла владимирской тяжеловозной породы, записана в ГПК, Линия Литого, Гаврило-Посадский конный завод")
                .photoFile("horse_02.jpg")
                .build();
        Horse horse03 = Horse.builder()
                .activity(true)
                .name("Кориолис")
                .description("Кориолис (Конкорд — Олимпия), серый мерин голштинской породы")
                .photoFile("horse_03.jpg")
                .build();
        Horse horse04 = Horse.builder()
                .activity(true)
                .name("Буся")
                .description("Буся — рыжая кобыла, спортивная помесь")
                .photoFile("horse_04.jpg")
                .build();
        Horse horse05 = Horse.builder()
                .activity(true)
                .name("Баронесса")
                .description("Баронесса (Baron fon Droft — Bayern Beat), серая кобыла першеронской породы, 2014-го года рождения")
                .photoFile("horse_05.jpg")
                .build();
        Horse horse06 = Horse.builder()
                .activity(true)
                .name("Гривка")
                .description("Гривка — Голштино — будённовская кобыла светло-ряжей масти")
                .photoFile("horse_06.jpg")
                .build();
        Horse horse07 = Horse.builder()
                .activity(true)
                .name("Дейзи")
                .description("Дейзи — гнедая кобыла будённовской породы")
                .photoFile("horse_07.jpg")
                .build();
        Horse horse08 = Horse.builder()
                .activity(true)
                .name("Санни")
                .description("Санни — вороная кобыла русской верховой породы")
                .photoFile("horse_08.jpg")
                .build();
        Horse horse09 = Horse.builder()
                .activity(true)
                .name("Игрушка")
                .description("Игрушка — пегая кобыла")
                .photoFile("horse_09.jpg")
                .build();
        Horse horse10 = Horse.builder()
                .activity(true)
                .name("Марти")
                .description("Марти — гнедая кобыла арабо-латвийской породы")
                .photoFile("horse_10.jpg")
                .build();
        Horse horse11 = Horse.builder()
                .activity(true)
                .name("Дейв")
                .description("Дейв — рыжий жеребец владимирской тяжеловозной породы")
                .photoFile("horse_11.jpg")
                .build();
        Horse horse12 = Horse.builder()
                .activity(true)
                .name("Зая")
                .description("Зая — вороная кобыла будённовской породы")
                .photoFile("horse_12.jpg")
                .build();
        Horse horse13 = Horse.builder()
                .activity(true)
                .name("Лори")
                .description("Лори — Голштино — будённовская кобыла светло-рыжей масти")
                .photoFile("horse_13.jpg")
                .build();
        Horse horse14 = Horse.builder()
                .activity(true)
                .name("Бак")
                .description("Бак — гнедой мерин орловской рысистой породы")
                .photoFile("horse_14.jpg")
                .build();
        Horse horse15 = Horse.builder()
                .activity(true)
                .name("Тайга")
                .description("Тайга — гнедая кобыла тяжеловозной пород")
                .photoFile("horse_15.jpg")
                .build();
        Horse horse16 = Horse.builder()
                .activity(true)
                .name("Кисси")
                .description("Кисси — соловая пони Уэльской породы")
                .photoFile("horse_16.jpg")
                .build();
        Horse horse17 = Horse.builder()
                .activity(true)
                .name("Марсик")
                .description("Марсик — рыжий мерин породы шетлендский пони")
                .photoFile("horse_17.jpg")
                .build();
        Horse horse18 = Horse.builder()
                .activity(true)
                .name("Снежок")
                .description("Снежок — серый мерин, Богатырский пони")
                .photoFile("horse_18.jpg")
                .build();
        horseService.saveAll(
                List.of(horse01,
                        horse02,
                        horse03,
                        horse04,
                        horse05,
                        horse06,
                        horse07,
                        horse08,
                        horse09,
                        horse10,
                        horse11,
                        horse12,
                        horse13,
                        horse14,
                        horse15,
                        horse16,
                        horse17,
                        horse18));
        LOGGER.info("Initialization of 'Horse' done");

        Coach coach01 = Coach.builder()
                .activity(true)
                .name("Петров Иван Александрович")
                .description("Чемпион и бронзовый призёр Олимпийских игр1980 года, семикратный чемпион СССР, серебряный призёр чемпионата Европы 1979, бронзовый призёр Чемпионата мира 1978, чемпион Спартакиады народов СССР, заслуженный мастер спорта, заслуженный деятель физической культуры, создатель школы верховой езды в Белорусской ССР, тренер чемпионов СССР, тренер чемпионов спартакиады народов СССР.")
                .photoFile("coach_01.jpg")
                .build();
        Coach coach02 = Coach.builder()
                .activity(true)
                .name("Леонов Артур Габитович")
                .description("Тренер преподаватель по конному спорту 1 категории, мастер спорта СССР. Неоднократный участник и призер Чемпионатов Башкирии и России по троеборью и конкуру. Обладатель командного кубка России по конкуру, участник чемпионата СССР по троеборью. Под руководством Артур Габитовича всадники становились призёрами Региональной России, Федеральных округов РФ.")
                .photoFile("coach_02.jpg")
                .build();
        Coach coach03 = Coach.builder()
                .activity(true)
                .name("Ольга Карамазова")
                .description("Mастер спорта по конному троеборью, «Лучший спортсмен по троеборью 2012 года”, участница Чемпионата Европы во Франции (Haras de Jardy) 2013г., тренер-преподаватель по физической культуре и конному спорту (МГАФК).")
                .photoFile("coach_03.jpg")
                .build();
        Coach coach04 = Coach.builder()
                .activity(true)
                .name("Антонина Быкова")
                .description("педагог-психолог (МПГУ); инструктор с двадцатилетним опытом работы; дипломированный тренер по верховой езде (ВНИИК); ученица Ивана Александровича Петрова; фотограф; автор тематических статей по конному спорту (журналы «Золотой Мустанг», «Конный Парк»), руководитель клуба")
                .photoFile("coach_04.jpg")
                .build();
        Coach coach05 = Coach.builder()
                .activity(true)
                .name("Алина Прохорик")
                .description("Тренер-преподаватель по физической культуре и конному спорту (МГАФК); тренер-инструктор по верховой езде, играющий тренер, специализация: конкур.")
                .photoFile("coach_05.jpg")
                .build();
        Coach coach06 = Coach.builder()
                .activity(true)
                .name("Светлана Акирова")
                .description("Клинический психолог (МГППУ); иппотерапевт («Наш Солнечный мир»); тренер-инструктор по верховой езде.")
                .photoFile("coach_06.jpg")
                .build();
        coachService.saveAll(
                List.of(coach01,
                        coach02,
                        coach03,
                        coach04,
                        coach05,
                        coach06));
        LOGGER.info("Initialization of 'Coach' done");

        User user01 = User.builder()
                .username("admin")
                .password(encoder.encode("admin"))
                .email("cavalierhorseclub@gmail.com")
                .activity(true)
                .build();
        User user02 = User.builder()
                .username("user")
                .password(encoder.encode("user"))
                .email("123@mail.ru")
                .activity(true)
                .build();
        userService.saveAll(
                List.of(user01, user02));
        LOGGER.info("Initialization of 'User' done");

        CertificateOrder certificateOrder01 = CertificateOrder.builder()
                .certificate(certificate01)
                .certificateDecoration(certificateDecoration01)
                .details("-")
                .orderStatus(OrderStatus.ACCEPTED)
                .eventDate(LocalDate.now())
                .owner("Иванов Иван Иванович")
                .user(user02)
                .build();
        CertificateOrder certificateOrder02 = CertificateOrder.builder()
                .certificate(certificate02)
                .certificateDecoration(certificateDecoration02)
                .details("г.Минск, пр-т Кутузова, 45-78")
                .orderStatus(OrderStatus.ACTIVATED)
                .eventDate(LocalDate.now())
                .owner("Петров Петр Петрович")
                .user(user02)
                .build();
        CertificateOrder certificateOrder03 = CertificateOrder.builder()
                .certificate(certificate03)
                .certificateDecoration(certificateDecoration03)
                .details("г.Минск, пр-т Грибоедова, 25-18")
                .orderStatus(OrderStatus.ACTIVATED)
                .eventDate(LocalDate.now())
                .owner("Сидоров Сидр Сидорович")
                .user(user02)
                .build();
        CertificateOrder certificateOrder04 = CertificateOrder.builder()
                .certificate(certificate04)
                .certificateDecoration(certificateDecoration04)
                .details("г.Пинск, ул. Красная, 15-73")
                .orderStatus(OrderStatus.CLOSED)
                .eventDate(LocalDate.now())
                .owner("Поликарпов Поликарп Поликарпович")
                .user(user01)
                .build();
        CertificateOrder certificateOrder05 = CertificateOrder.builder()
                .certificate(certificate05)
                .certificateDecoration(certificateDecoration02)
                .details("г.Псков, пр-т Любимова, 42-58")
                .orderStatus(OrderStatus.CLOSED)
                .eventDate(LocalDate.now())
                .owner("Николаев Николай Николаевич")
                .user(user01)
                .build();
        CertificateOrder certificateOrder06 = CertificateOrder.builder()
                .certificate(certificate06)
                .certificateDecoration(certificateDecoration02)
                .details("г.Слуцк, пр-т Мира, 65-71")
                .orderStatus(OrderStatus.ACTIVATED)
                .eventDate(LocalDate.now())
                .owner("Алексндров Александр Александрович")
                .user(user02)
                .build();

        CertificateOrder certificateOrder07 = CertificateOrder.builder()
                .certificate(certificate01)
                .certificateDecoration(certificateDecoration01)
                .details("-")
                .orderStatus(OrderStatus.ACCEPTED)
                .eventDate(LocalDate.now())
                .owner("Иванов Иван Иванович")
                .user(user02)
                .build();
        CertificateOrder certificateOrder08 = CertificateOrder.builder()
                .certificate(certificate02)
                .certificateDecoration(certificateDecoration02)
                .details("г.Минск, пр-т Кутузова, 45-78")
                .orderStatus(OrderStatus.ACTIVATED)
                .eventDate(LocalDate.now())
                .owner("Петров Петр Петрович")
                .user(user02)
                .build();
        CertificateOrder certificateOrder09 = CertificateOrder.builder()
                .certificate(certificate03)
                .certificateDecoration(certificateDecoration03)
                .details("г.Минск, пр-т Грибоедова, 25-18")
                .orderStatus(OrderStatus.ACTIVATED)
                .eventDate(LocalDate.now())
                .owner("Сидоров Сидр Сидорович")
                .user(user02)
                .build();
        CertificateOrder certificateOrder10 = CertificateOrder.builder()
                .certificate(certificate04)
                .certificateDecoration(certificateDecoration04)
                .details("г.Пинск, ул. Красная, 15-73")
                .orderStatus(OrderStatus.CLOSED)
                .eventDate(LocalDate.now())
                .owner("Поликарпов Поликарп Поликарпович")
                .user(user01)
                .build();
        CertificateOrder certificateOrder11 = CertificateOrder.builder()
                .certificate(certificate05)
                .certificateDecoration(certificateDecoration02)
                .details("г.Псков, пр-т Любимова, 42-58")
                .orderStatus(OrderStatus.CLOSED)
                .eventDate(LocalDate.now())
                .owner("Николаев Николай Николаевич")
                .user(user01)
                .build();
        CertificateOrder certificateOrder12 = CertificateOrder.builder()
                .certificate(certificate06)
                .certificateDecoration(certificateDecoration02)
                .details("г.Слуцк, пр-т Мира, 65-71")
                .orderStatus(OrderStatus.ACTIVATED)
                .eventDate(LocalDate.now())
                .owner("Алексндров Александр Александрович")
                .user(user02)
                .build();

        CertificateOrder certificateOrder13 = CertificateOrder.builder()
                .certificate(certificate01)
                .certificateDecoration(certificateDecoration01)
                .details("-")
                .orderStatus(OrderStatus.ACCEPTED)
                .eventDate(LocalDate.now())
                .owner("Иванов Иван Иванович")
                .user(user02)
                .build();
        CertificateOrder certificateOrder14 = CertificateOrder.builder()
                .certificate(certificate02)
                .certificateDecoration(certificateDecoration02)
                .details("г.Минск, пр-т Кутузова, 45-78")
                .orderStatus(OrderStatus.ACTIVATED)
                .eventDate(LocalDate.now())
                .owner("Петров Петр Петрович")
                .user(user02)
                .build();
        CertificateOrder certificateOrder15 = CertificateOrder.builder()
                .certificate(certificate03)
                .certificateDecoration(certificateDecoration03)
                .details("г.Минск, пр-т Грибоедова, 25-18")
                .orderStatus(OrderStatus.ACTIVATED)
                .eventDate(LocalDate.now())
                .owner("Сидоров Сидр Сидорович")
                .user(user02)
                .build();
        CertificateOrder certificateOrder16 = CertificateOrder.builder()
                .certificate(certificate04)
                .certificateDecoration(certificateDecoration04)
                .details("г.Пинск, ул. Красная, 15-73")
                .orderStatus(OrderStatus.CLOSED)
                .eventDate(LocalDate.now())
                .owner("Поликарпов Поликарп Поликарпович")
                .user(user01)
                .build();
        CertificateOrder certificateOrder17 = CertificateOrder.builder()
                .certificate(certificate05)
                .certificateDecoration(certificateDecoration02)
                .details("г.Псков, пр-т Любимова, 42-58")
                .orderStatus(OrderStatus.CLOSED)
                .eventDate(LocalDate.now())
                .owner("Николаев Николай Николаевич")
                .user(user01)
                .build();
        CertificateOrder certificateOrder18 = CertificateOrder.builder()
                .certificate(certificate06)
                .certificateDecoration(certificateDecoration02)
                .details("г.Слуцк, пр-т Мира, 65-71")
                .orderStatus(OrderStatus.ACTIVATED)
                .eventDate(LocalDate.now())
                .owner("Алексндров Александр Александрович")
                .user(user02)
                .build();

        certificateOrderService.saveAll(List.of(certificateOrder01,
                certificateOrder02,
                certificateOrder03,
                certificateOrder04,
                certificateOrder05,
                certificateOrder06,
                certificateOrder07,
                certificateOrder08,
                certificateOrder09,
                certificateOrder10,
                certificateOrder11,
                certificateOrder12,
                certificateOrder13,
                certificateOrder14,
                certificateOrder15,
                certificateOrder16,
                certificateOrder17,
                certificateOrder18));
        LOGGER.info("Initialization of 'CertificateOrder' done");

        List<Attachment> attachments = imageFileList.getImageFileList().stream()
                .map(fileName ->
                        Attachment.builder()
                                .fileName(fileName)
                                .build())
                .collect(Collectors.toList());
        attachmentService.saveAll(attachments);

        TelegramBot telegramBot = context.getBean("telegramBot", TelegramBot.class);
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(telegramBot);
            LOGGER.info("TelegramBot registered");
        } catch (TelegramApiException e) {
            LOGGER.warn("Telegram bot isn't registered");
        }
    }
}
