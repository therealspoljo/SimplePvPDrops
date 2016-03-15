package com.therealspoljo.simplepvpdrops;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.therealspoljo.simplepvpdrops.commands.SimplePvPDrops;
import com.therealspoljo.simplepvpdrops.listeners.PlayerDeathListener;
import com.therealspoljo.simplepvpdrops.listeners.PlayerDropItemListener;
import com.therealspoljo.simplepvpdrops.utilities.Config;

public class Main extends JavaPlugin {

    private static Main instance;
    private Config config, langConfig;

    @Override
    public void onEnable() {
	instance = this;

	config = Config.createConfig(this, "config");
	langConfig = Config.createConfig(this, "lang");

	registerCommands();
	registerListeners();
    }

    @Override
    public void onDisable() {
	instance = null;

	config = null;
	langConfig = null;
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
}