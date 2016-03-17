package com.therealspoljo.simplepvpdrops;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.therealspoljo.simplepvpdrops.commands.SimplePvPDrops;
import com.therealspoljo.simplepvpdrops.listeners.PlayerDeathListener;
import com.therealspoljo.simplepvpdrops.listeners.PlayerDropItemListener;
import com.therealspoljo.simplepvpdrops.utilities.Config;
import com.therealspoljo.simplepvpdrops.utilities.ConfigUtils;

public class Main extends JavaPlugin {

    private static Main instance;
    private Config config, langConfig;
    private List<String> droppableItems;

    @Override
    public void onEnable() {
	instance = this;

	config = Config.createConfig(this, "config");
	langConfig = Config.createConfig(this, "lang");

	reloadDroppableItems();
	registerCommands();
	registerListeners();
    }

    @Override
    public void onDisable() {
	instance = null;

	config = null;
	langConfig = null;

	droppableItems = null;
    }

    public static Main getInstance() {
	return instance;
    }

    @Override
    public Config getConfig() {
	return config;
    }

    public Config getLangConfig() {
	return langConfig;
    }

    private void registerCommands() {
	getCommand("SimplePvPDrops".toLowerCase()).setExecutor(new SimplePvPDrops());
    }

    private void registerListeners() {
	PluginManager pluginManager = Bukkit.getPluginManager();

	pluginManager.registerEvents(new PlayerDeathListener(), this);
	pluginManager.registerEvents(new PlayerDropItemListener(), this);
    }

    public void reloadDroppableItems() {
	droppableItems = ConfigUtils.getDroppable();
    }

    public List<String> getDroppableItems() {
	return droppableItems;
    }
}