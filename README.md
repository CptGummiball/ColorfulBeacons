# ColorfulBeacons

**ColorfulBeacons** is a Spigot plugin that allows players to change the color of a beacon's beam without the need for colored glass blocks. Players can execute a command while looking at a beacon to set its color. The plugin also saves the colors, ensuring they persist across server restarts.

## Features

- Change the color of a beacon's beam without using colored glass.
- Colors can be specified using either Minecraft color names (e.g., `BLUE`, `YELLOW`) or HEX codes (e.g., `FF0000` for red).
- Colors are saved in a configuration file, preserving changes after server restarts.
- Permission system to control who can change beacon colors.
- Tab-completion for color names.

## Requirements

- Spigot server version 1.20 or higher.

## Installation

1. Download the latest version of **ColorfulBeacons**.
2. Place the JAR file into the `plugins` folder of your Spigot server.
3. Start or restart your server.
4. The plugin will automatically create a configuration file to store beacon colors.

## Usage

To change the color of a beacon beam, follow these steps:

1. Look at the beacon you wish to change.
2. Execute the command: `setbeaconcolor <color|hex>`
3. Replace `<color>` with a Minecraft color name (e.g., `BLUE`) or a hexadecimal color code (e.g., `FF0000`).

### Permissions

To allow players to change beacon colors, grant them the permission: `colorfulbeacons.use`
By default, this permission is set to `true`, allowing all players to use the command.

## Example

To change the beacon color to blue, you would use: `/setbeaconcolor blue`
To change it to red using a HEX code, use: `/setbeaconcolor FF0000`

## Support

If you encounter any issues or have suggestions for improvements, please feel free to open an issue in this repository.

## License

This project is licensed under the [MIT License](LICENSE).
