package org.cptgummiball.colorfulBeacons;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.block.Beacon;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ColorfulBeacons extends JavaPlugin {

    private Map<String, Color> beaconColors = new HashMap<>();

    @Override
    public void onEnable() {
        saveDefaultConfig();
        loadBeaconColors();
        getCommand("setbeaconcolor").setExecutor(this);
        getCommand("setbeaconcolor").setTabCompleter(new ColorTabCompleter());
    }

    @Override
    public void onDisable() {
        saveBeaconColors();
    }

    private void loadBeaconColors() {
        FileConfiguration config = getConfig();
        for (String key : config.getKeys(false)) {
            beaconColors.put(key, Color.fromRGB(config.getInt(key)));
        }
    }

    private void saveBeaconColors() {
        FileConfiguration config = getConfig();
        for (Map.Entry<String, Color> entry : beaconColors.entrySet()) {
            config.set(entry.getKey(), entry.getValue().asRGB());
        }
        saveConfig();
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("setbeaconcolor") && sender instanceof Player) {
            Player player = (Player) sender;
            if (!player.hasPermission("colorfulbeacons.use")) {
                player.sendMessage("You do not have permission to change the beacon color.");
                return true;
            }
            if (args.length != 1) {
                player.sendMessage("Usage: /setbeaconcolor <color|hex>");
                return true;
            }

            Color color;
            String colorInput = args[0].toUpperCase();

            // Check if input is a Minecraft color name or a HEX code
            if (isMinecraftColor(colorInput)) {
                color = getColorFromName(colorInput);
            } else {
                try {
                    color = Color.fromRGB(Integer.parseInt(colorInput, 16));
                } catch (NumberFormatException e) {
                    player.sendMessage("Please provide a valid Minecraft color name or HEX color code.");
                    return true;
                }
            }

            if (player.getTargetBlock(null, 5).getType() == Material.BEACON) {
                Beacon beacon = (Beacon) player.getTargetBlock(null, 5).getState();
                beaconColors.put(player.getUniqueId().toString(), color);
                beacon.update();
                player.sendMessage("The beacon beam color has been changed.");
            } else {
                player.sendMessage("You must be looking at a beacon.");
            }
        }
        return true;
    }

    private boolean isMinecraftColor(String colorName) {
        return colorName.matches("BLACK|DARK_BLUE|DARK_GREEN|DARK_AQUA|DARK_RED|DARK_PURPLE|GOLD|GRAY|DARK_GRAY|BLUE|GREEN|AQUA|RED|LIGHT_PURPLE|YELLOW|WHITE");
    }

    private Color getColorFromName(String colorName) {
        switch (colorName) {
            case "BLACK":
                return Color.fromRGB(0, 0, 0);
            case "DARK_BLUE":
                return Color.fromRGB(0, 0, 170);
            case "DARK_GREEN":
                return Color.fromRGB(0, 170, 0);
            case "DARK_AQUA":
                return Color.fromRGB(0, 170, 170);
            case "DARK_RED":
                return Color.fromRGB(170, 0, 0);
            case "DARK_PURPLE":
                return Color.fromRGB(170, 0, 170);
            case "GOLD":
                return Color.fromRGB(255, 170, 0);
            case "GRAY":
                return Color.fromRGB(170, 170, 170);
            case "DARK_GRAY":
                return Color.fromRGB(85, 85, 85);
            case "BLUE":
                return Color.fromRGB(85, 85, 255);
            case "GREEN":
                return Color.fromRGB(0, 255, 0);
            case "AQUA":
                return Color.fromRGB(85, 255, 255);
            case "RED":
                return Color.fromRGB(255, 85, 85);
            case "LIGHT_PURPLE":
                return Color.fromRGB(255, 85, 255);
            case "YELLOW":
                return Color.fromRGB(255, 255, 85);
            case "WHITE":
                return Color.fromRGB(255, 255, 255);
            default:
                return Color.fromRGB(255, 255, 255); // Default to white if no match
        }
    }

    private class ColorTabCompleter implements TabCompleter {
        @Override
        public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
            List<String> completions = new ArrayList<>();
            if (args.length == 1) {
                // Add Minecraft color names
                completions.add("BLACK");
                completions.add("DARK_BLUE");
                completions.add("DARK_GREEN");
                completions.add("DARK_AQUA");
                completions.add("DARK_RED");
                completions.add("DARK_PURPLE");
                completions.add("GOLD");
                completions.add("GRAY");
                completions.add("DARK_GRAY");
                completions.add("BLUE");
                completions.add("GREEN");
                completions.add("AQUA");
                completions.add("RED");
                completions.add("LIGHT_PURPLE");
                completions.add("YELLOW");
                completions.add("WHITE");
            }
            return completions;
        }
    }
}
