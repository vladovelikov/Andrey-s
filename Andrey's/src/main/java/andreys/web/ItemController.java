package andreys.web;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import andreys.model.binding.ItemAddBindingModel;
import andreys.model.entity.service.ItemServiceModel;
import andreys.service.ItemService;
import javax.validation.Valid;

@Controller
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;
    private final ModelMapper modelMapper;

    @Autowired
    public ItemController(ItemService itemService, ModelMapper modelMapper) {
        this.itemService = itemService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String add(Model model) {
        if(!model.containsAttribute("itemAddBindingModel")) {
            model.addAttribute("itemAddBindingModel", new ItemAddBindingModel());
        }
        return "add-item";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid @ModelAttribute("itemAddBindingModel")ItemAddBindingModel itemAddBindingModel,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("itemAddBindingModel", itemAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.itemAddBindingModel", bindingResult);
            return "redirect:add";
        }

        this.itemService.addItem(this.modelMapper.map(itemAddBindingModel, ItemServiceModel.class));

        return "redirect:/";
    }

    @GetMapping("/details")
    public ModelAndView details(@RequestParam("id") String id, ModelAndView modelAndView) {
        modelAndView.addObject("item", this.itemService.findById(id));
        modelAndView.setViewName("details-item");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id) {
        this.itemService.deleteItem(id);
        return "redirect:/";
    }
}
