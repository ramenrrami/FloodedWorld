/*
 * Copyright (C) 2026 raammi
 */

package club.ironpvp.floodedWorld.commands;

import club.ironpvp.floodedWorld.listeners.NightVisionListener;
import club.ironpvp.floodedWorld.manager.NightVisionManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Set;

import static net.kyori.adventure.text.Component.text;

public class NightVisionCommand implements CommandExecutor, TabCompleter {
    private final NightVisionManager manager;
    public NightVisionCommand(NightVisionManager manager) {
        this.manager = manager;
    }

    private final Set<String> ON_STRING = Set.of("true", "on");
    private final Set<String> OFF_STRING = Set.of("false", "off");

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (!(sender instanceof Player player)) return true;

        boolean toggler;
        if (args.length > 0) {
            String input = args[0].toLowerCase();
            if (ON_STRING.contains(input)) {
                toggler = true;
            } else if (OFF_STRING.contains(input)) {
                toggler = false;
            } else {
                sender.sendMessage(text("Invalid input: " + input, NamedTextColor.RED));
                return true;
            }
        } else {
            toggler = !manager.isNightVision(player);
        }

        manager.setNightVision(player, toggler);
        if (toggler) {
            manager.addPot(player);
        } else {
            manager.onRemv(player);
        }

        sendToggleMsg(player, toggler);
        return true;
    }

    private void sendToggleMsg(Player player, boolean state) {
        String stateText = state ? "enabled" : "disabled";

        Component component = text("NightVision ", NamedTextColor.GREEN).append(text(stateText, NamedTextColor.YELLOW));
        player.sendMessage(component);
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (args.length != 1) return List.of();

        String input = args[0].toLowerCase();
        return List.of("on", "off").stream()
                .filter(s -> s.startsWith(input)).toList();
    }
}
