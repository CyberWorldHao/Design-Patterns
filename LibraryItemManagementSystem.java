/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab7.LibraryItemManagementSystem;

/**
 *
 * @author CWH
 */
import java.util.HashMap;
import java.util.ArrayList;

interface CommandInterface {

    public HashMap<String, ArrayList<String>> execute(String key, String value);

}

class AddCommand implements CommandInterface {

    Item item;

    public AddCommand(Item item) {
        this.item = item;
    }

    @Override
    public HashMap<String, ArrayList<String>> execute(String key, String value) {
        return item.addItem(key, value);
    }
}

class DeleteCommand implements CommandInterface {

    Item item;

    public DeleteCommand(Item item) {
        this.item = item;
    }

    @Override
    public HashMap<String, ArrayList<String>> execute(String key, String value) {
        return item.deleteItem(key, value);
    }
}

class MoveCommand implements CommandInterface {

    Item item;
    CommandInterface[] commands;
    String originalValue;

    public MoveCommand(CommandInterface[] commands, String originalValue) {
        this.commands = commands;
        this.originalValue = originalValue;
    }

    @Override
    public HashMap<String, ArrayList<String>> execute(String key, String newValue) {
        String[] values = {originalValue, newValue};
        HashMap<String, ArrayList<String>> tempHashMap = null;
        
        System.out.println("Move the item from the " + originalValue + " category to " + newValue + " category");
        for (int i = 0; i < commands.length; i++) {
            tempHashMap = commands[i].execute(key, values[i]);
        }
        return tempHashMap;
    }

}

class Item {

    HashMap<String, ArrayList<String>> listCategoryItem = new HashMap<>();
    CategoryList categoryList;

    public Item() {
        categoryList = new CategoryList(new ArrayList<>());
    }

    public HashMap<String, ArrayList<String>> addItem(String key, String value) {
        if (listCategoryItem.get(key) != null && listCategoryItem.get(key).size() > 0) {
            categoryList = new CategoryList(listCategoryItem.get(key));
        }
        categoryList.addCategory(value);
        listCategoryItem.put(key, categoryList.getCategoryList());
        System.out.println("Item '" + key + "' has been added to the '" + value + "' Category");
        return listCategoryItem;
    }

    public HashMap<String, ArrayList<String>> deleteItem(String key, String value) {
        if (listCategoryItem.get(key) != null && listCategoryItem.get(key).size() > 0) {
            categoryList = new CategoryList(listCategoryItem.get(key));
            categoryList.deleteCategory(value);
            System.out.println("Item '" + key + "' has been deleted from the '" + value + "' Category");
        } else {
            System.out.println("This item didn't has any category");
        }
        return listCategoryItem;
    }

}

class CategoryList {

    ArrayList<String> list = new ArrayList<>();

    public CategoryList(ArrayList<String> list) {
        this.list = list;
    }

    public void addCategory(String value) {
        list.add(value);
    }

    public void deleteCategory(String value) {
        list.remove(value);
    }

    public ArrayList<String> getCategoryList() {
        return list;
    }
}

class ItemManager { // Invoker

    CommandInterface command;
    HashMap<String, ArrayList<String>> list;
    String itemKey;

    public void setCommand(CommandInterface command) {
        this.command = command;
    }

    public void invokeProcess(String key, String value) {
        itemKey = key;
        list = command.execute(key, value);
    }

    @Override
    public String toString() {
        StringBuilder stringBuild = new StringBuilder();
        stringBuild.append("Item '").append(itemKey).append("' belongs to these categories: ").append(list.get(itemKey));
        return stringBuild.toString();
    }
}

public class LibraryItemManagementSystem {

    public static void main(String[] args) {
        ItemManager itemManager = new ItemManager();
        Item item1 = new Item();
        AddCommand addCommand = new AddCommand(item1);
        DeleteCommand deleteCommand = new DeleteCommand(item1);

        System.out.println("--- Question 2 ---");
        itemManager.setCommand(addCommand);
        itemManager.invokeProcess("Duet", "CD");
        itemManager.invokeProcess("Duet", "New Releases");
        System.out.println(itemManager);
        itemManager.setCommand(deleteCommand);
        itemManager.invokeProcess("Duet", "New Releases");
        System.out.println(itemManager);

        System.out.println("\n\n--- Question 3 Move Function ---");
        Item item2 = new Item();
        addCommand = new AddCommand(item2);
        deleteCommand = new DeleteCommand(item2);
        CommandInterface[] commands = {deleteCommand, addCommand};
        itemManager.setCommand(addCommand);
        itemManager.invokeProcess("A Beautiful Mind", "CD");
        System.out.println(itemManager);
//      Move function 
        MoveCommand moveCommand = new MoveCommand(commands, "CD");
        itemManager.setCommand(moveCommand);
        itemManager.invokeProcess("A Beautiful Mind", "Book");
        System.out.println(itemManager);
    }
}
