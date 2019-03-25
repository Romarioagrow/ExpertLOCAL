package expertshop.controllers.category;
import expertshop.controllers.ProductController;
import expertshop.domain.categories.Category;
import expertshop.domain.categories.SubCategory;
import expertshop.domain.categories.Type;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class ComputersController {
    private final ProductController productController;

    @GetMapping("/computers-all")
    public String showAllComputers(
            Model model,
            @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin,
            @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax,
            @RequestParam(required = false, name = "brand", defaultValue = "") String brand,
            @RequestParam(required = false, name = "country", defaultValue = "") String country,
            @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        productController.showProductsByCategory(Category.Computers, model, sortmin, sortmax, brand, country, sortby);
        return "pages/computers/computers";
    }

    @GetMapping("/computers-pc")
    public String showPC(
            Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        productController.showProductsBySubCategory(SubCategory.PC, model, sortmin, sortmax, brand, country, sortby);
        return "pages/computers/computers-pc";
    }

    @GetMapping("/notebooks")
    public String showNotebooks(
            Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        productController.showProductsByType(Type.Notebook, model, sortmin, sortmax, brand, country, sortby);
        return "pages/computers/computers-notebooks";
    }

    @GetMapping("/computer-parts")
    public String showPCP(
            Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        productController.showProductsBySubCategory(SubCategory.ComputerParts, model, sortmin, sortmax, brand, country, sortby);
        return "pages/computers/computer-parts";
    }

    @GetMapping("/computers-office")
    public String showComputersOffice(
            Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        productController.showProductsBySubCategory(SubCategory.ComputersOffice, model, sortmin, sortmax, brand, country, sortby);
        return "pages/computers/computers-office";
    }

    @GetMapping("/computers-accessory")
    public String showComputersAccessory(
            Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        productController.showProductsBySubCategory(SubCategory.ComputersAccessory, model, sortmin, sortmax, brand, country, sortby);
        return "pages/computers/computers-accessory";
    }
}
