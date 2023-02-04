package com.ben.wandwars.helpers.itemStackHelping;

import com.ben.wandwars.Main;
import com.ben.wandwars.wands.wandInterfaces.Wand;
import com.ben.wandwars.wands.wandInterfaces.WandCraftable;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import javax.lang.model.type.PrimitiveType;
import javax.lang.model.type.TypeKind;
import java.util.ArrayList;
import java.util.List;

public class ItemStackHelper {

    protected ItemStackHelper() {};

    //helper method that makes it easier to make an itemstack
    public static ItemStack createItemStack(String name, Material material) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(name);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public static ItemStack createItemStack(String name, Material material, String[] lore) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(name);

        List<String> refinedLore = new ArrayList<>();
        for(String singleLore : lore) {
            refinedLore.add(singleLore);
        }
        itemMeta.setLore(refinedLore);

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    //crafts wands. is used as a helper.
    public static boolean craft(Wand wand, Main main) {

        if(wand instanceof WandCraftable) {

            WandCraftable wandCraftable = (WandCraftable) wand;

            if (wandCraftable.getCraftingID() == null) {
                return false;
            }

            ShapedRecipe wandRecipe = new ShapedRecipe(new NamespacedKey(main, wandCraftable.getCraftingID()), wand.getItem());

            wandRecipe.shape(wandCraftable.getRecipe());
            for (Character craftingReference : wandCraftable.getCraftingReference().keySet()) {
                wandRecipe.setIngredient(craftingReference, wandCraftable.getCraftingReference().get(craftingReference));
            }

            Bukkit.addRecipe(wandRecipe);

            return true;
        }
        return false;
    }

    public static void addPersistentDataContainer(ItemStack itemStack, String key, Object data, PersistentDataType dataType) {


        itemStack.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(Main.getInstance(), key), dataType, data);
    }
}
