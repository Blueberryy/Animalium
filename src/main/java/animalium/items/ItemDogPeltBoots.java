package animalium.items;

import animalium.ModArmourMaterials;
import animalium.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ItemDogPeltBoots extends ArmorItem {

	public ItemDogPeltBoots(ModArmourMaterials material, EquipmentSlotType slot, Properties properties) {
		super(material, slot, properties);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public String getArmorTexture(ItemStack is, Entity entity, EquipmentSlotType slot, String type) {
		if (is.getItem() == ModItems.DOG_PELT_BOOTS)
			return "animalium:textures/items/dog_boots.png";
		else
			return null;
	}

	@Override
	public boolean getIsRepairable(ItemStack armour, ItemStack material) {
		return material.getItem() == ModItems.WILD_DOG_PELT;
	}
	
	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		if (entity instanceof PlayerEntity) {
			ItemStack is = ((PlayerEntity) entity).getItemStackFromSlot(EquipmentSlotType.FEET);
			if (!is.isEmpty() && is.getItem() == this && !entity.isSneaking()) {
				if (entity.isSprinting() && entity.onGround) {
					entity.fallDistance = 0.0F;
					float f1 = entity.rotationYaw * ((float) Math.PI / 180F);
					entity.setMotion(entity.getMotion().add((double) (-MathHelper.sin(f1) * 1F + 0.5F), 0.0D, (double) (MathHelper.cos(f1) * 1F + 0.5F)));
				}
			}
		}
	}
/*
	@SubscribeEvent
	public void onEntitySprint(LivingUpdateEvent e) {
		if (e.getEntityLiving() instanceof PlayerEntity) {
			ItemStack is = ((PlayerEntity) e.getEntityLiving()).getItemStackFromSlot(EquipmentSlotType.FEET);
			if (!is.isEmpty() && is.getItem() == this && !e.getEntityLiving().isSneaking()) {
			      if (e.getEntityLiving().isSprinting() && e.getEntityLiving().onGround) {
			         float f1 = e.getEntityLiving().rotationYaw * ((float)Math.PI / 180F);
			         e.getEntityLiving().setMotion(e.getEntityLiving().getMotion().add((double)(-MathHelper.sin(f1) * 1F + 0.5F), 0.0D, (double)(MathHelper.cos(f1) * 1F + 0.5F)));
			      }
				}
		}
	}
*/
	@SubscribeEvent
	public void onEntityJump(LivingJumpEvent e) {
		if (e.getEntityLiving() instanceof PlayerEntity) {
			ItemStack is = ((PlayerEntity) e.getEntityLiving()).getItemStackFromSlot(EquipmentSlotType.FEET);
			if (!is.isEmpty() && is.getItem() == this && !e.getEntityLiving().isSneaking()) {
			      float f = 0.82F;
			      Vec3d vec3d = e.getEntityLiving().getMotion();
			      e.getEntityLiving().setMotion(vec3d.x, (double)f, vec3d.z);
			      if (e.getEntityLiving().isSprinting()) {
			         float f1 = e.getEntityLiving().rotationYaw * ((float)Math.PI / 180F);
			         e.getEntityLiving().setMotion(e.getEntityLiving().getMotion().add((double)(-MathHelper.sin(f1) * 0.2F), 0.0D, (double)(MathHelper.cos(f1) * 0.2F)));
			      }

			      e.getEntityLiving().isAirBorne = true;
				
			//	e.getEntityLiving().getVmotionY += 0.4D;
				}
		}
	}
}