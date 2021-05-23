package me.happybavarian07.ConfirmMessage;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class CommandCompleter implements TabCompleter {
	
	List<String> commandargs1 = new ArrayList<String>();
	List<String> commandargs2 = new ArrayList<String>();
	
	@Override
	public List<String> onTabComplete(CommandSender s, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("confirmmessage") || cmd.getName().equals("cm") || cmd.getName().equalsIgnoreCase("confirmm") || cmd.getName().equalsIgnoreCase("cmessage")) {
			if(args.length == 1) {
				if(s.hasPermission("ConfirmMessage.commands.confirmmessage.commands")) {
					if(commandargs1.isEmpty()) {
						commandargs1.add("Commands");
//						commandargs1.add("Aliases");
					}
					
					List<String> result = new ArrayList<String>();
					if (args.length == 1) {
						for (String a : commandargs1) {
							if (a.toLowerCase().startsWith(args[0].toLowerCase()))
								result.add(a);
						}
						return result;
					}
				} else {
					return null;
				}
			}
//			if(args.length == 2) {
//				if(s.hasPermission("ConfirmMessage.commands.confirmmessage.aliases")) {
//					if(commandargs2.isEmpty()) {
//						if(!ConfirmMessage.plugin.getConfig().getStringList("Commands").isEmpty()) {
//							commandargs2.addAll(ConfirmMessage.plugin.getConfig().getStringList("Commands"));
//						}
//					}
//					
//					List<String> result = new ArrayList<String>();
//					if (args.length == 2) {
//						for (String a : commandargs2) {
//							if (a.toLowerCase().startsWith(args[0].toLowerCase()))
//								result.add(a);
//						}
//						return result;
//					}
//				} else {
//					return null;
//				}
//			}
		}
		return null;
	}
}
