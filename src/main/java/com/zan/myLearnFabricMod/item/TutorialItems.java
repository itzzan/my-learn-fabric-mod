package com.zan.myLearnFabricMod.item;

import com.zan.myLearnFabricMod.MyLearnFabricMod;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

/**
 * @Author Zan
 * @Create 2025/4/24 15:05
 * @ClassName: TutorialItems
 * @Description : 创建物品——实体类
 */
public class TutorialItems {

    /**
     * 新物品进行注册
     * 注册成功后，未加入到物品表里，可以通过命令：/give @s my-learn-fabric-mod:suspicious_substance 获取
     */
    public static final Item SUSPICIOUS_SUBSTANCE = register("suspicious_substance", Item::new, new Item.Settings());

    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        // 创建唯一注册Key
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MyLearnFabricMod.MOD_ID, name));

        // 创建项目实例
        Item item = itemFactory.apply(settings.registryKey(itemKey));

        // 注册该项目实例
        Registry.register(Registries.ITEM, itemKey, item);

        return item;
    }

    public static void initialize() {
    }
}
