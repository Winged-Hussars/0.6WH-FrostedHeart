/*
 * Copyright (c) 2022-2024 TeamMoeg
 *
 * This file is part of Frosted Heart.
 *
 * Frosted Heart is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * Frosted Heart is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Frosted Heart. If not, see <https://www.gnu.org/licenses/>.
 *
 */

package com.teammoeg.frostedheart.util.client;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.util.ResourceLocation;

public class AtlasUV extends TexturedUV {
	int gridW;
    public AtlasUV(ResourceLocation texture, int w, int h, int gridW) {
        super(texture, 0, 0, w, h);
        this.gridW=gridW;
    }
    public AtlasUV(ResourceLocation texture, int w, int h, int gridW, int tw, int th) {
        super(texture, 0, 0, w, h, tw, th);
        this.gridW=gridW;
    }
	public AtlasUV(ResourceLocation texture, int x, int y, int w, int h, int gridW, int tw, int th) {
		super(texture, x, y, w, h, tw, th);
		this.gridW=gridW;
	}
	public AtlasUV(String texture, int x, int y, int w, int h, int gridW, int tw, int th) {
		super(texture, x, y, w, h, tw, th);
		this.gridW=gridW;
	}
	public AtlasUV(String texture, int w, int h, int gridW, int tw, int th) {
		super(texture, 0, 0, w, h, tw, th);
		this.gridW=gridW;
	}
	public void blitAtlas(MatrixStack s, int targetX, int targetY, int gridIndex) {
		super.blitAtlas(s, targetX, targetY, gridIndex % gridW, gridIndex / gridW);
	}
	public void blitAtlas(MatrixStack s, int targetX, int targetY, Point loc, int gridIndex) {
		// TODO Auto-generated method stub
		super.blitAtlas(s, targetX, targetY, loc, gridIndex % gridW, gridIndex / gridW);
	}
}
