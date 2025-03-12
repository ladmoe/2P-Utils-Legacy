package LadsMod.Modules;

import net.minecraft.client.Minecraft;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.IChatComponent;

public class ChatCopy {

    public static boolean receiveChat(IChatComponent msg) {
        if (!msg.toString().contains(":") &&!msg.toString().contains("[")) return false;
        String input = msg.getUnformattedText();
        int colonIndex = input.indexOf(':');
        if (colonIndex >= 0) {
            String suggest = input.substring(colonIndex + 2);
            Minecraft.getMinecraft().thePlayer.addChatMessage(getSuggest(msg.getFormattedText(),suggest));
        }
        return true;
    }
    private static String formatColorCodes(String msg) {
        for(int i = 0; i < msg.length() - 1; ++i) {
            if (msg.charAt(i) == '\\' && msg.charAt(i + 1) == '&') {
                i += 2;
            } else if (msg.charAt(i) == '&' && msg.charAt(i + 1) != ' ') {
                msg = msg.substring(0, i) + '&' + msg.substring(i + 1);
            }
        }

        return msg.replace("\\", "");
    }
    private static ChatComponentText getSuggest(String msg, String suggestCmd) {
        ChatComponentText cct = new ChatComponentText(formatColorCodes(msg));
        ChatStyle style = new ChatStyle();
        style.setChatClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, suggestCmd));
        cct.setChatStyle(style);
        return cct;
    }
}
