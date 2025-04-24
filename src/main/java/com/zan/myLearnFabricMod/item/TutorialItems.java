package com.zan.myLearnFabricMod.item;

import com.zan.myLearnFabricMod.MyLearnFabricMod;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
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

    /**
     * 新物品组的Key（创建新物品组）
     */
    public static final RegistryKey<ItemGroup> CUSTOM_ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(MyLearnFabricMod.MOD_ID, "item_group"));

    /**
     * 创建物品组
     */
    public static final ItemGroup CUSTOM_ITEM_GROUP = FabricItemGroup.builder()
            // 物品组图标选择
            .icon(() -> new ItemStack(TutorialItems.SUSPICIOUS_SUBSTANCE))
            .displayName(Text.translatable("itemGroup.fabric_docs_reference"))
            .build();

    public static void initialize() {
        // 注册新物品组
        Registry.register(Registries.ITEM_GROUP, CUSTOM_ITEM_GROUP_KEY, CUSTOM_ITEM_GROUP);
        // 将新物品添加到物品栏中
        ItemGroupEvents.modifyEntriesEvent(CUSTOM_ITEM_GROUP_KEY).register(itemGroup -> {
            itemGroup.add(TutorialItems.SUSPICIOUS_SUBSTANCE);
            // 还可以继续添加
        });
        // 让新物品可以堆肥（堆肥桶）
        CompostingChanceRegistry.INSTANCE.add(TutorialItems.SUSPICIOUS_SUBSTANCE, 0.3f);
        // 让物品可作燃料（跟煤炭一样）
        FuelRegistryEvents.BUILD.register((builder, context) -> {
            builder.add(TutorialItems.SUSPICIOUS_SUBSTANCE, 30 * 20);
        });
    }
}
