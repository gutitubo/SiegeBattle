package Lib;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_14_R1.DataWatcher;
import net.minecraft.server.v1_14_R1.DataWatcherObject;
import net.minecraft.server.v1_14_R1.DataWatcherRegistry;
import net.minecraft.server.v1_14_R1.EntityPlayer;
import net.minecraft.server.v1_14_R1.PacketPlayOutEntityMetadata;

public class KuruKuruUtils {
    // Twitter: @uTen2c
	private KuruKuruUtils() {}
	
    public static void attachKuruKuru(Player player) {
        setMeta(player, 7);
    }

    public static void removeKuruKuru(Player player) {
        setMeta(player, 0);
    }

    private static void setMeta(Player player, int number) {
        EntityPlayer ep = ((CraftPlayer)player).getHandle();
        DataWatcher dw = new DataWatcher(ep);
        DataWatcherObject<Byte> obj = new DataWatcherObject<Byte>(7, DataWatcherRegistry.a);
        dw.register(obj, (byte) number);
        PacketPlayOutEntityMetadata packet = new PacketPlayOutEntityMetadata(ep.getId(), dw, true);
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
        }
    }
}
