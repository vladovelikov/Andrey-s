package andreys.service.impl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import andreys.model.entity.Category;
import andreys.model.entity.Item;
import andreys.model.entity.service.ItemServiceModel;
import andreys.repository.ItemRepository;
import andreys.service.CategoryService;
import andreys.service.ItemService;
import andreys.view.ItemViewModel;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, ModelMapper modelMapper, CategoryService categoryService) {
        this.itemRepository = itemRepository;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
    }


    @Override
    public void addItem(ItemServiceModel itemServiceModel) {
        Item item = this.modelMapper.map(itemServiceModel, Item.class);

        Category category = this.categoryService
                .findByCategoryName(itemServiceModel.getCategory().getCategoryName());

        item.setCategory(category);

        this.itemRepository.saveAndFlush(item);

    }

    @Override
    public List<ItemViewModel> findAllItems() {
        return this.itemRepository.findAll().stream()
                .map(item -> {
                    ItemViewModel itemViewModel = this.modelMapper.map(item, ItemViewModel.class);

                    itemViewModel.setImgUrl(String.format("/img/%s-%s.jpg", item.getGender(),
                            item.getCategory().getName().name()));

                    return itemViewModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    public ItemViewModel findById(String id) {
        return this.itemRepository.findById(id).map(item -> {
            ItemViewModel itemViewModel = this.modelMapper.map(item, ItemViewModel.class);

            itemViewModel.setImgUrl(String.format("/img/%s-%s.jpg", item.getGender(),
                    item.getCategory().getName().name()));

            return itemViewModel;
        })
                .orElse(null);
    }

    @Override
    public void deleteItem(String id) {
        this.itemRepository.deleteById(id);
    }
}
