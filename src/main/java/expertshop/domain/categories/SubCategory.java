package expertshop.domain.categories;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public enum SubCategory {
    TV, LED19_25, LED32, LED32_Smart,
    Multimedia, Sputnik,
    KitchenBig, KitchenBuiltin, CookingDevice, KitchenSmall, KitchenAccessory,
    ForWashing, ForCleaning, ForClothes, HomeAccessory,
    /// No SubCategories in Climate
    PC, ComputerParts, ComputersOffice, ComputersAccessory,
}
