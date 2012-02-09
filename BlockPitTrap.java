package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class BlockPitTrap extends Block {
	
	public BlockPitTrap(int i)
	{
		super(i, mod_Rogue.pitTrapTexture, Material.wood);
        float f = 0.0625F;
        setBlockBounds(f, 0.0F, f, 1.0F - f, 0.03125F, 1.0F - f);
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public boolean canPlaceBlockAt(World world, int i, int j, int k)
    {
        return world.isBlockNormalCube(i, j - 1, k) || world.getBlockId(i, j - 1, k) == Block.fence.blockID;
    }
    
    public void onBlockRemoval(World world, int i, int j, int k)
    {
        int l = world.getBlockMetadata(i, j, k);
        if(l > 0)
        {
            world.notifyBlocksOfNeighborChange(i, j, k, blockID);
            world.notifyBlocksOfNeighborChange(i, j - 1, k, blockID);
        }
        super.onBlockRemoval(world, i, j, k);
    }
    
    public void setBlockBoundsBasedOnState(IBlockAccess iblockaccess, int i, int j, int k)
    {
        boolean flag = iblockaccess.getBlockMetadata(i, j, k) == 1;
        float f = 0.0625F;
        if(flag)
        {
            setBlockBounds(f, 0.0F, f, 1.0F - f, 0.03125F, 1.0F - f);
        } else
        {
            setBlockBounds(f, 0.0F, f, 1.0F - f, 0.0625F, 1.0F - f);
        }
    }


    public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity)
    {
    	stepOnPlate(world, i, j, k);       
    }
    
    
    
    private void stepOnPlate(World world, int i, int j, int k)
    {
        boolean platePressed = world.getBlockMetadata(i, j, k) == 1;
        boolean somethingOnPlate = false;
        float f = 0.125F;
        List list = null;

        list = world.getEntitiesWithinAABBExcludingEntity(null, AxisAlignedBB.getBoundingBoxFromPool((float)i + f, j, (float)k + f, (float)(i + 1) - f, (double)j + 0.25D, (float)(k + 1) - f));
        if(list.size() > 0)
        {
        	somethingOnPlate = true;
        }
        
        if(somethingOnPlate && !platePressed)
        {
            world.setBlockMetadataWithNotify(i, j, k, 1);
            world.markBlocksDirty(i, j, k, i, j, k);
            trapSound(world,i,j,k);
            trapEffect(world,i,j,k);
        }
    }
    
    public void trapSound(World world, int x, int y, int z){
    	 world.playAuxSFXAtEntity(null, 1003, x, y, z, 0);
    }
    
    public void trapEffect(World world, int x, int y, int z){
    	 world.setBlockWithNotify(x, y, z, 0);
         world.setBlockWithNotify(x, y-1, z, 0);
         world.setBlockWithNotify(x, y-2, z, 0);
         
         double d = (float)x + 0.5F;
         double d1 = (float)y + 0.7F;
         double d2 = (float)z + 0.5F;
         
         Random random = new Random();
         
         for(int i = 0; i <10 ;i++){
        	 world.spawnParticle("explode", (float)x + random.nextFloat(), (float)y + random.nextFloat(), (float)z + random.nextFloat(), 0.0D, 0.0D, 0.0D);

         }
    }
    
    
    

}
