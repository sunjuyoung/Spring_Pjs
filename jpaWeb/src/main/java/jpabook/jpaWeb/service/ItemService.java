package jpabook.jpaWeb.service;

import jpabook.jpaWeb.controller.BookForm;
import jpabook.jpaWeb.domain.item.Book;
import jpabook.jpaWeb.domain.item.Item;
import jpabook.jpaWeb.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId, UpdateItemDto updateItemDto){
        Book findItem = (Book)itemRepository.findOne(itemId);

        findItem.change(updateItemDto.getName(),updateItemDto.getPrice(),updateItemDto.getStockQuantity());


/*        findItem.setPrice(book.getPrice());
        findItem.setStockQuantity(book.getStockQuantity());
        findItem.setIsbn(book.getIsbn());
        findItem.setName(book.getName());
        findItem.setAuthor(book.getAuthor());
        findItem.setId(book.getId());*/

        //itemRepository.save(findItem);
    }


    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }
}
