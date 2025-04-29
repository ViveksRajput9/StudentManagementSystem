from PIL import Image
from PIL import Image

def resize_png_to_icon(input_path, output_path, size=(30, 30)):
    """
    Resize a PNG image to the specified icon size.

    :param input_path: Path to the input PNG image.
    :param output_path: Path to save the resized icon image.
    :param size: Tuple specifying the desired size (width, height).
    """
    try:
        # Open the original image
        with Image.open(input_path) as img:
            # Resize the image
            img = img.resize(size, Image.LANCZOS)
            # Save the resized image
            img.save(output_path, format='PNG')
            print(f"Image resized and saved to {output_path}")
    except Exception as e:
        print(f"An error occurred: {e}")

# Example usage
input_image_path = "addAdmin.png"  # Replace with your input image path
output_image_path = 'AddAdmin.png'  # Replace with your desired output path
resize_png_to_icon(input_image_path, output_image_path, size=(32, 32))
# def convert_png_to_ico(png_path, ico_path, icon_sizes):
#     try:
#         # Open the PNG image
#         img = Image.open("AddStudent.png")
        
#         # Ensure the image has an alpha channel for transparency
#         if img.mode != "RGBA":
#             img = img.convert("RGBA")
        
#         # Save as ICO file with specified sizes
#         img.save(ico_path, format="ICO", sizes=icon_sizes)
#         print(f"App icon saved successfully as: {ico_path}")
#     except Exception as e:
#         print(f"An error occurred: {e}")

# if __name__ == "__main__":
#     # File paths
#     png_path = "your_image.png"  # Replace with your PNG file path
#     ico_path = "app_icon.ico"    # Output icon file name
    
#     # Icon sizes (standard app icon sizes)
#     icon_sizes = [(16, 16), (32, 32), (48, 48), (64, 64), (128, 128), (256, 256)]
    
#     # Convert PNG to ICO
#     convert_png_to_ico(png_path, ico_path, icon_sizes)
