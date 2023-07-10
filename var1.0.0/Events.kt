package com.github.Ringoame196

import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.block.Dropper
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack

class Events : Listener {
    @EventHandler
    fun onPlayerInteractEvent(e: PlayerInteractEvent) {
        val player = e.player
        val item = e.item
        if (item?.type != Material.LAVA_BUCKET) {
            return
        }

        val block = e.clickedBlock?.state as? Dropper
        if (block?.type != Material.DROPPER) {
            return
        }
        if (block.customName != "ゴミ箱") {
            return
        }

        e.isCancelled = true
        player.sendMessage("${ChatColor.RED}焼却！！！")
        block.inventory.clear()
        player.playSound(player.location, Sound.BLOCK_FIRE_EXTINGUISH, 1f, 1f)
        for (i in 0..5) {
            player.location.world?.spawnParticle(Particle.LAVA, block.location, 10)
        }
        player.inventory.setItemInMainHand(ItemStack(Material.BUCKET))
    }
}
