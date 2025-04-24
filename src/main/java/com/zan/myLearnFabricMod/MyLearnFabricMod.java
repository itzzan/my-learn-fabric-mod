package com.zan.myLearnFabricMod;

import com.zan.myLearnFabricMod.item.TutorialItems;
import net.fabricmc.api.ModInitializer;

/**
 * 初始化类
 */
public class MyLearnFabricMod implements ModInitializer {

    /**
     * 标识唯一的ModId
     */
    public static final String MOD_ID = "my-learn-fabric-mod";

    @Override
    public void onInitialize() {
        // 初始化新物品类
        TutorialItems.initialize();
    }
}
