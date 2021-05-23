package me.happybavarian07.ConfirmMessage;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MainCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("confirmmessage") || cmd.getName().equals("cm") || cmd.getName().equalsIgnoreCase("confirmm") || cmd.getName().equalsIgnoreCase("cmessage")) {
			if(s instanceof Player) {
				Player p = (Player) s;
				if(p.hasPermission("ConfirmMessage.commands.confirmmessage")) {
					if(args[0].equalsIgnoreCase("Commands")) {
						if(args.length == 1) {
							/*
							 * Check if the Player has Permissions
							 * List all Commands
							 * if not Empty
							 */
							if(!p.hasPermission("ConfirmMessage.commands.confirmmessage.commands")) {
								p.sendMessage("§cYou don't have Permissions to do that!");
								return true;
							}
							if(!ConfirmMessage.plugin.getConfig().getStringList("Commands").isEmpty()) {
								List<String> commands = ConfirmMessage.plugin.getConfig().getStringList("Commands");
								p.sendMessage("§6+----------------------------------------+");
								for(int i = 0; i < commands.size(); i++) {
									p.sendMessage((i+1) + ". §a" + commands.get(i));
								}
								p.sendMessage("§6+----------------------------------------+");
							} else {
								p.sendMessage("§cThere are no Commands registered in the Config!");
							}
						} else {
							p.sendMessage("Too many / few arguments");
						}
						return true;
					}
//					if(args[0].equalsIgnoreCase("Aliases")) {
//						if(args.length == 2) {
//							/*
//							 * Check if the Player has Permissions
//							* Check if Command args[1] is valid
//							* list all Aliases if not Empty
//							* if Empty send Message
//							*/
//							if(p.hasPermission("ConfirmMessage.commands.confirmmessage.aliases")) {
//								if(ConfirmMessage.plugin.getConfig().getStringList("Commands").contains(args[1])) {
//									if(ConfirmMessage.plugin.getConfig().getStringList("Commands." + args[1] + ".Aliases").isEmpty()) {
//										List<String> aliases = ConfirmMessage.plugin.getConfig().getStringList("Commands." + args[1] + ".Aliases");
//										p.sendMessage("§6+----------------------------------------+");
//										for(int i = 0; i < aliases.size(); i++) {
//											p.sendMessage((i+1) + ". §a" + aliases.get(i));
//										}
//										p.sendMessage("§6+----------------------------------------+");
//									} else {
//										p.sendMessage("§cThere are no Aliases registered for §6" + args[1] + "§c in the Config!");
//									}
//								} else {
//									p.sendMessage("§6" + args[1] + " §cis not a Command from the Config!");
//								}
//							}
//						} else {
//							p.sendMessage("Too many / few arguments");
//						}
//						return true;
//					}
					p.sendMessage("§cCheck your spelling §6" + args[0] + "§c is not an Argument!");
					return true;
				} else {
					p.sendMessage("§cYou don't have Permissions to do that!");
					return true;
				}
			} else {
				s.sendMessage("§cYou have to be a Player!");
				return true;
			}
		}
		return true;
	}
}
