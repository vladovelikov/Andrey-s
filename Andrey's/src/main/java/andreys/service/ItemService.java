package andreys.service;
import andreys.model.entity.service.ItemServiceModel;
import andreys.view.ItemViewModel;
import java.util.List;

public interface ItemService {

    void addItem(ItemServiceModel itemServiceModel);
    List<ItemViewModel> findAllItems();
    ItemViewModel findById(String id);
    void deleteItem(String id);
}
