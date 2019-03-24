package expertshop.domain.categories;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public enum Type {
    // Электроника (Теле-видео-аудио)
    /*
     * Телеизоры
     * Спутниковая техника
     * Мультимедийные системы
     * Портативная техника
     * Кабели и шнуры
    */
    Reciver_DVBT2, ///
    PlayerDVD,
    Charger,
    TV_Antenna,///
    Kronshteyn,
    MusicCenter,///
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
    ChaynikElectro,///
    Toaster,///
    Blender,///
    Mixer,///
    Juicer,///
    CoffeeMaker,///
    MeatGrinder,///

    Combine,///
        Chopper,
    MultiCooker,///
    Microwave,///
    Breakfast,///
        Vafelnitsa,
        Shashlychnitsa,
        YogurtMaker,

    Kitchen_Accessory,
    Kitchen_Tool,///
    Dish,///
    Water_Filter,///
    Kitchen_Vesi,///

    Stove,///
    Oven,///
    Fridge,///
    Freezer,///

    BuiltIn_Oven,///
    BuiltIn_Stove,///
    Vytyazhka,///


    // Техника для дома
    /*
    * Стирка
    * Уборка
    * Для одежды
    * Аксессуары для дома
    */
    WashingMachine,///
        WashingMachine_Accessory,
    ClothesDryer,///

    VacuumCleaner,///
        VacuumCleaner_Accessory,
        VacuumCleaner_Filter,
        Pilesbornik,

    IroningBoard,///
    Utug,///
        Iron_Accessory,
    SewingMachine,///


    // Климат
    /*
    Кондиционеры
    Водонагреватели
    Газовые колонки
    Обогреватели
    Вентиляторы
    */
    Conditioner,///

    WaterHeater,///

    GasWaterHeater,///

    ElectricHeaters,///
        Thermometer,

    Ventilator,///

    // Компьютеры и офис
    /*
    ПК и переферия
    Ноутбуки
    Комплектующие
    Офисная техника
    Аксессуары
    */
    UsbFlash,///
    MemoryStick,///
    Наушники,///

    PC,///
    Monitor,///
    Keyboard,///
    Mouse,///
    Kolonki,///
    Naushniki,///
    Microfon,///
    WebCam,///

    Notebook,/// для всех подкатегорий одна страница с разными фильтрами
        Notebook_Accessory,

    Printer,///
    Scanner,///
    Projector,///


    // Портативная техника


    // Smart техника
    Tablet,
        Tablet_Accessory,

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

    // Товары для отдыха ///ПЕРЕРАСПРЕДЕЛИТЬ В ОСТАЛЬНЫЕ КАТЕГОРИИ
    Mangal,
    Lamp,
    Bluetooth_Garnitura,
    ProtectGlass,
    Camera,
    Wireless_Sound,
    Radio_Tel,///
    MobilePhone,///
    SmartPhone,
    Reader,



    PhotoCamera, VideoCamera, PortableRadio,

     SmartHeaders, BluetoothTech


}
