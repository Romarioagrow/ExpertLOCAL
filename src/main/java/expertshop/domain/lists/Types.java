package expertshop.domain.lists;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public enum Types {
    // Электроника (Теле-видео-аудио)
    /*
     * Телеизоры
     * Спутниковая техника
     * Мультимедийные системы
     * Портативная техника
     * Кабели и шнуры
    */
    Reciver_DVBT2,
    PlayerDVD,
    Charger,
    TV_Antenna,
    Kronshteyn,
    MusicCenter,
    TV,
    Cords,

    // Техника для кухни
    /*
    * Крупная техника
    * Встраиваемая техника
    * Маленькая техника
    * Готовит еду
    * Аксессуары
    */
    Kitchen_Accessory,
    Shashlychnitsa,
    Blender,
    Chopper,
    Mixer,
    Vafelnitsa,
    Kitchen_Vesi,
    Vytyazhka,
    YogurtMaker,
    Combine,
    CoffeeMaker,
    Kitchen_Tool,
    MultiCooker,
    MeatGrinder,
    Microwave,
    Stove,
    Dish,
    Juicer,
    Toaster,
    Water_Filter,
    ChaynikElectro,
    Oven,
    Fridge,
    Freezer,
    BuiltIn_Oven,
    BuiltIn_Stove,

    // Техника для дома
    /*
    * Стирка
    * Уборка
    * Для одежды
    * Аксессуары для дома
    */
    Pilesbornik,
    VacuumCleaner_Filter,
    IroningBoard,
    VacuumCleaner,
    WashingMachine,
    ClothesDryer,
    Iron,
    SewingMachine,
    VacuumCleaner_Accessory,
    WashingMachine_Accessory,
    Iron_Accessory,

    // Климат
    /*
    Кондиционеры
    Вентиляторы
    Обогреватели
    Водонагреватели
    Газовые колонки
    */
    Ventilator,
    WaterHeater,
    Gas_WaterHeater,
    Conditioner,
    Heaters,
    Thermometer,

    // Приборы персонального ухода
    /*
    Для бритья и стрижки
    Для ухода за волосами
    Аксуссуары
    */
    Razor,
    HaircutMachine,
    Trimmer,
    PloykaWithShipci,
    Styler,
    HairDryer,
    Razor_Accessory,

    // Автотехника
    /*
    Автомагнитолы
    Акустика и автозвук
    Видеорегистраторы
    Навигация
    Автоаксессуары
    */
    AutoKolonki,
    AutoMagnitola,
    CarCharger,
    VideoRegistrator,
    AutoSound,
    Compressor,

    // Компьютеры и офис
    /*
    ПК и переферия
    Ноутбуки
    Комплектующие
    Офисная техника
    Аксессуары
    */
    UsbFlash,
    Notebook_Accessory,
    Tablet_Accessory,
    WebCam,
    MemoryStick,
    PC,
    Notebook,
    Naushniki,
    Kolonki,
    Keyboard,
    Mouse,
    Printer,
    Monitor,
    Projector,
    Tablet,

    // Товары для отдыха ///ПЕРЕРАСПРЕДЕЛИТЬ В ОСТАЛЬНЫЕ КАТЕГОРИИ
    Mangal,
    Lamp,
    Bluetooth_Garnitura,
    ProtectGlass,
    Camera,
    Wireless_Sound,
    Radio_Tel,
    MobilePhone,
    SmartPhone,
    Reader,

}
