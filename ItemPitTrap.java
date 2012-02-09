package net.minecraft.src;

public class ItemPitTrap extends Item{
	
    public ItemPitTrap(int id)
    {
        super(id);
        setMaxStackSize(8);
		setIconIndex(Item.cake.iconIndex);
    }
    
    public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l)
    {
        BlockPitTrap pitTrap = (BlockPitTrap)mod_Rogue.pitTrapBlock;
        if(pitTrap.canPlaceBlockAt(world, i, j, k)){	
	    	world.setBlockAndMetadataWithNotify(i, j, k, pitTrap.blockID, 0);
	    
	        itemstack.stackSize--;
	        return true;
        }
        return false;

    }

}
